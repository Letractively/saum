package br.com.meganet.facebookAPI.infra.json;
import java.lang.reflect.Field;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.apache.commons.beanutils.PropertyUtils;

public class SimplesPropertySetStrategy extends PropertySetStrategy {

	public void setProperty(Object bean, String key, Object value) throws JSONException {
		setProperty(bean, key, value, new JsonConfig());
	}

	@SuppressWarnings("unchecked")
	public void setProperty(Object bean, String key, Object value, JsonConfig jsonConfig) throws JSONException {
		if (bean instanceof Map) {
			((Map<String, Object>) bean).put(key, value);
		} else {
			if (!jsonConfig.isIgnorePublicFields()) {
				try {
					Field field = bean.getClass().getField(key);
					if (field != null) {
						field.set(bean, value);
					}
				} catch (Exception e) {
					_setProperty(bean, key, value);
				}
			} else {
				_setProperty(bean, key, value);
			}
		}
	}

	private void _setProperty(Object bean, String key, Object value) {
		if (PropertyUtils.isWriteable(bean, key)) {
			try {
				PropertyUtils.setSimpleProperty(bean, key, value);
			} catch (Exception e) {
				throw new JSONException(e);
			}
		}
	}

}