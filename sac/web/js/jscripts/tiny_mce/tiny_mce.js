var tinymce = {
	majorVersion :"3",
	minorVersion :"2.2.3",
	releaseDate :"2009-03-26",
	_init : function() {
		var o = this, k = document, l = window, j = navigator, b = j.userAgent, h, a, g, f, e, m;
		o.isOpera = l.opera && opera.buildNumber;
		o.isWebKit = /WebKit/.test(b);
		o.isIE = !o.isWebKit && !o.isOpera && (/MSIE/gi).test(b)
				&& (/Explorer/gi).test(j.appName);
		o.isIE6 = o.isIE && /MSIE [56]/.test(b);
		o.isGecko = !o.isWebKit && /Gecko/.test(b);
		o.isMac = b.indexOf("Mac") != -1;
		o.isAir = /adobeair/i.test(b);
		if (l.tinyMCEPreInit) {
			o.suffix = tinyMCEPreInit.suffix;
			o.baseURL = tinyMCEPreInit.base;
			o.query = tinyMCEPreInit.query;
			return
		}
		o.suffix = "";
		a = k.getElementsByTagName("base");
		for (h = 0; h < a.length; h++) {
			if (m = a[h].href) {
				if (/^https?:\/\/[^\/]+$/.test(m)) {
					m += "/"
				}
				f = m ? m.match(/.*\// )[0] : ""
			}
		}
		function c(d) {
			if (d.src
					&& /tiny_mce(|_dev|_src|_gzip|_jquery|_prototype).js/
							.test(d.src)) {
				if (/_(src|dev)\.js/g.test(d.src)) {
					o.suffix = "_src"
				}
				if ((e = d.src.indexOf("?")) != -1) {
					o.query = d.src.substring(e + 1)
				}
				o.baseURL = d.src.substring(0, d.src.lastIndexOf("/"));
				if (f && o.baseURL.indexOf("://") == -1) {
					o.baseURL = f + o.baseURL
				}
				return o.baseURL
			}
			return null
		}
		a = k.getElementsByTagName("script");
		for (h = 0; h < a.length; h++) {
			if (c(a[h])) {
				return
			}
		}
		g = k.getElementsByTagName("head")[0];
		if (g) {
			a = g.getElementsByTagName("script");
			for (h = 0; h < a.length; h++) {
				if (c(a[h])) {
					return
				}
			}
		}
		return
	},
	is : function(b, a) {
		var c = typeof (b);
		if (!a) {
			return c != "undefined"
		}
		if (a == "array" && (b.hasOwnProperty && b instanceof Array)) {
			return true
		}
		return c == a
	},
	each : function(d, a, c) {
		var e, b;
		if (!d) {
			return 0
		}
		c = c || d;
		if (typeof (d.length) != "undefined") {
			for (e = 0, b = d.length; e < b; e++) {
				if (a.call(c, d[e], e, d) === false) {
					return 0
				}
			}
		} else {
			for (e in d) {
				if (d.hasOwnProperty(e)) {
					if (a.call(c, d[e], e, d) === false) {
						return 0
					}
				}
			}
		}
		return 1
	},
	map : function(b, c) {
		var d = [];
		tinymce.each(b, function(a) {
			d.push(c(a))
		});
		return d
	},
	grep : function(b, c) {
		var d = [];
		tinymce.each(b, function(a) {
			if (!c || c(a)) {
				d.push(a)
			}
		});
		return d
	},
	inArray : function(c, d) {
		var e, b;
		if (c) {
			for (e = 0, b = c.length; e < b; e++) {
				if (c[e] === d) {
					return e
				}
			}
		}
		return -1
	},
	extend : function(f, d) {
		var c, b = arguments;
		for (c = 1; c < b.length; c++) {
			d = b[c];
			tinymce.each(d, function(a, e) {
				if (typeof (a) !== "undefined") {
					f[e] = a
				}
			})
		}
		return f
	},
	trim : function(a) {
		return (a ? "" + a : "").replace(/^\s*|\s*$/g, "")
	},
	create : function(j, a) {
		var i = this, b, e, f, g, d, h = 0;
		j = /^((static) )?([\w.]+)(:([\w.]+))?/.exec(j);
		f = j[3].match(/(^|\.)(\w+)$/i)[2];
		e = i.createNS(j[3].replace(/\.\w+$/, ""));
		if (e[f]) {
			return
		}
		if (j[2] == "static") {
			e[f] = a;
			if (this.onCreate) {
				this.onCreate(j[2], j[3], e[f])
			}
			return
		}
		if (!a[f]) {
			a[f] = function() {
			};
			h = 1
		}
		e[f] = a[f];
		i.extend(e[f].prototype, a);
		if (j[5]) {
			b = i.resolve(j[5]).prototype;
			g = j[5].match(/\.(\w+)$/i)[1];
			d = e[f];
			if (h) {
				e[f] = function() {
					return b[g].apply(this, arguments)
				}
			} else {
				e[f] = function() {
					this.parent = b[g];
					return d.apply(this, arguments)
				}
			}
			e[f].prototype[f] = e[f];
			i.each(b, function(c, k) {
				e[f].prototype[k] = b[k]
			});
			i.each(a, function(c, k) {
				if (b[k]) {
					e[f].prototype[k] = function() {
						this.parent = b[k];
						return c.apply(this, arguments)
					}
				} else {
					if (k != f) {
						e[f].prototype[k] = c
					}
				}
			})
		}
		i.each(a["static"], function(c, k) {
			e[f][k] = c
		});
		if (this.onCreate) {
			this.onCreate(j[2], j[3], e[f].prototype)
		}
	},
	walk : function(c, b, d, a) {
		a = a || this;
		if (c) {
			if (d) {
				c = c[d]
			}
			tinymce.each(c, function(f, e) {
				if (b.call(a, f, e, d) === false) {
					return false
				}
				tinymce.walk(f, b, d, a)
			})
		}
	},
	createNS : function(d, c) {
		var b, a;
		c = c || window;
		d = d.split(".");
		for (b = 0; b < d.length; b++) {
			a = d[b];
			if (!c[a]) {
				c[a] = {}
			}
			c = c[a]
		}
		return c
	},
	resolve : function(d, c) {
		var b, a;
		c = c || window;
		d = d.split(".");
		for (b = 0, a = d.length; b < a; b++) {
			c = c[d[b]];
			if (!c) {
				break
			}
		}
		return c
	},
	addUnload : function(e, d) {
		var c = this, a = window;
		e = {
			func :e,
			scope :d || this
		};
		if (!c.unloads) {
			function b() {
				var f = c.unloads, h, i;
				if (f) {
					for (i in f) {
						h = f[i];
						if (h && h.func) {
							h.func.call(h.scope, 1)
						}
					}
					if (a.detachEvent) {
						a.detachEvent("onbeforeunload", g);
						a.detachEvent("onunload", b)
					} else {
						if (a.removeEventListener) {
							a.removeEventListener("unload", b, false)
						}
					}
					c.unloads = h = f = a = b = null;
					if (window.CollectGarbage) {
						window.CollectGarbage()
					}
				}
			}
			function g() {
				var h = document;
				if (h.readyState == "interactive") {
					function f() {
						h.detachEvent("onstop", f);
						b();
						h = null
					}
					h.attachEvent("onstop", f);
					window.setTimeout( function() {
						h.detachEvent("onstop", f)
					}, 0)
				}
			}
			if (a.attachEvent) {
				a.attachEvent("onunload", b);
				a.attachEvent("onbeforeunload", g)
			} else {
				if (a.addEventListener) {
					a.addEventListener("unload", b, false)
				}
			}
			c.unloads = [ e ]
		} else {
			c.unloads.push(e)
		}
		return e
	},
	removeUnload : function(c) {
		var a = this.unloads, b = null;
		tinymce.each(a, function(e, d) {
			if (e && e.func == c) {
				a.splice(d, 1);
				b = c;
				return false
			}
		});
		return b
	},
	explode : function(a, b) {
		return a ? tinymce.map(a.split(b || ","), tinymce.trim) : a
	},
	_addVer : function(b) {
		var a;
		if (!this.query) {
			return b
		}
		a = (b.indexOf("?") == -1 ? "?" : "&") + this.query;
		if (b.indexOf("#") == -1) {
			return b + a
		}
		return b.replace("#", a + "#")
	}
};
window.tinymce = tinymce;
tinymce._init();
tinymce.create("tinymce.util.Dispatcher", {
	scope :null,
	listeners :null,
	Dispatcher : function(a) {
		this.scope = a || this;
		this.listeners = []
	},
	add : function(a, b) {
		this.listeners.push( {
			cb :a,
			scope :b || this.scope
		});
		return a
	},
	addToTop : function(a, b) {
		this.listeners.unshift( {
			cb :a,
			scope :b || this.scope
		});
		return a
	},
	remove : function(a) {
		var b = this.listeners, c = null;
		tinymce.each(b, function(e, d) {
			if (a == e.cb) {
				c = a;
				b.splice(d, 1);
				return false
			}
		});
		return c
	},
	dispatch : function() {
		var f, d = arguments, e, b = this.listeners, g;
		for (e = 0; e < b.length; e++) {
			g = b[e];
			f = g.cb.apply(g.scope, d);
			if (f === false) {
				break
			}
		}
		return f
	}
});
( function() {
	var a = tinymce.each;
	tinymce
			.create(
					"tinymce.util.URI",
					{
						URI : function(e, g) {
							var f = this, h, d, c;
							g = f.settings = g || {};
							if (/^(mailto|tel|news|javascript|about):/i.test(e)
									|| /^\s*#/.test(e)) {
								f.source = e;
								return
							}
							if (e.indexOf("/") === 0 && e.indexOf("//") !== 0) {
								e = (g.base_uri ? g.base_uri.protocol || "http"
										: "http")
										+ "://mce_host" + e
							}
							if (e.indexOf(":/") === -1 && e.indexOf("//") !== 0) {
								e = (g.base_uri.protocol || "http")
										+ "://mce_host"
										+ f.toAbsPath(g.base_uri.path, e)
							}
							e = e.replace(/@@/g, "(mce_at)");
							e = /^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@]*):?([^:@]*))?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/
									.exec(e);
							a( [ "source", "protocol", "authority", "userInfo",
									"user", "password", "host", "port",
									"relative", "path", "directory", "file",
									"query", "anchor" ], function(b, j) {
								var k = e[j];
								if (k) {
									k = k.replace(/\(mce_at\)/g, "@@")
								}
								f[b] = k
							});
							if (c = g.base_uri) {
								if (!f.protocol) {
									f.protocol = c.protocol
								}
								if (!f.userInfo) {
									f.userInfo = c.userInfo
								}
								if (!f.port && f.host == "mce_host") {
									f.port = c.port
								}
								if (!f.host || f.host == "mce_host") {
									f.host = c.host
								}
								f.source = ""
							}
						},
						setPath : function(c) {
							var b = this;
							c = /^(.*?)\/?(\w+)?$/.exec(c);
							b.path = c[0];
							b.directory = c[1];
							b.file = c[2];
							b.source = "";
							b.getURI()
						},
						toRelative : function(b) {
							var c = this, d;
							if (b === "./") {
								return b
							}
							b = new tinymce.util.URI(b, {
								base_uri :c
							});
							if ((b.host != "mce_host" && c.host != b.host && b.host)
									|| c.port != b.port
									|| c.protocol != b.protocol) {
								return b.getURI()
							}
							d = c.toRelPath(c.path, b.path);
							if (b.query) {
								d += "?" + b.query
							}
							if (b.anchor) {
								d += "#" + b.anchor
							}
							return d
						},
						toAbsolute : function(b, c) {
							var b = new tinymce.util.URI(b, {
								base_uri :this
							});
							return b.getURI(this.host == b.host ? c : 0)
						},
						toRelPath : function(g, h) {
							var c, f = 0, d = "", e, b;
							g = g.substring(0, g.lastIndexOf("/"));
							g = g.split("/");
							c = h.split("/");
							if (g.length >= c.length) {
								for (e = 0, b = g.length; e < b; e++) {
									if (e >= c.length || g[e] != c[e]) {
										f = e + 1;
										break
									}
								}
							}
							if (g.length < c.length) {
								for (e = 0, b = c.length; e < b; e++) {
									if (e >= g.length || g[e] != c[e]) {
										f = e + 1;
										break
									}
								}
							}
							if (f == 1) {
								return h
							}
							for (e = 0, b = g.length - (f - 1); e < b; e++) {
								d += "../"
							}
							for (e = f - 1, b = c.length; e < b; e++) {
								if (e != f - 1) {
									d += "/" + c[e]
								} else {
									d += c[e]
								}
							}
							return d
						},
						toAbsPath : function(e, f) {
							var c, b = 0, g = [], d;
							d = /\/$/.test(f) ? "/" : "";
							e = e.split("/");
							f = f.split("/");
							a(e, function(h) {
								if (h) {
									g.push(h)
								}
							});
							e = g;
							for (c = f.length - 1, g = []; c >= 0; c--) {
								if (f[c].length == 0 || f[c] == ".") {
									continue
								}
								if (f[c] == "..") {
									b++;
									continue
								}
								if (b > 0) {
									b--;
									continue
								}
								g.push(f[c])
							}
							c = e.length - b;
							if (c <= 0) {
								return "/" + g.reverse().join("/") + d
							}
							return "/" + e.slice(0, c).join("/") + "/"
									+ g.reverse().join("/") + d
						},
						getURI : function(d) {
							var c, b = this;
							if (!b.source || d) {
								c = "";
								if (!d) {
									if (b.protocol) {
										c += b.protocol + "://"
									}
									if (b.userInfo) {
										c += b.userInfo + "@"
									}
									if (b.host) {
										c += b.host
									}
									if (b.port) {
										c += ":" + b.port
									}
								}
								if (b.path) {
									c += b.path
								}
								if (b.query) {
									c += "?" + b.query
								}
								if (b.anchor) {
									c += "#" + b.anchor
								}
								b.source = c
							}
							return b.source
						}
					})
})();
( function() {
	var a = tinymce.each;
	tinymce.create("static tinymce.util.Cookie", {
		getHash : function(d) {
			var b = this.get(d), c;
			if (b) {
				a(b.split("&"), function(e) {
					e = e.split("=");
					c = c || {};
					c[unescape(e[0])] = unescape(e[1])
				})
			}
			return c
		},
		setHash : function(j, b, g, f, i, c) {
			var h = "";
			a(b, function(e, d) {
				h += (!h ? "" : "&") + escape(d) + "=" + escape(e)
			});
			this.set(j, h, g, f, i, c)
		},
		get : function(i) {
			var h = document.cookie, g, f = i + "=", d;
			if (!h) {
				return
			}
			d = h.indexOf("; " + f);
			if (d == -1) {
				d = h.indexOf(f);
				if (d != 0) {
					return null
				}
			} else {
				d += 2
			}
			g = h.indexOf(";", d);
			if (g == -1) {
				g = h.length
			}
			return unescape(h.substring(d + f.length, g))
		},
		set : function(i, b, g, f, h, c) {
			document.cookie = i + "=" + escape(b)
					+ ((g) ? "; expires=" + g.toGMTString() : "")
					+ ((f) ? "; path=" + escape(f) : "")
					+ ((h) ? "; domain=" + h : "") + ((c) ? "; secure" : "")
		},
		remove : function(e, b) {
			var c = new Date();
			c.setTime(c.getTime() - 1000);
			this.set(e, "", c, b, c)
		}
	})
})();
tinymce.create("static tinymce.util.JSON", {
	serialize : function(e) {
		var c, a, d = tinymce.util.JSON.serialize, b;
		if (e == null) {
			return "null"
		}
		b = typeof e;
		if (b == "string") {
			a = "\bb\tt\nn\ff\rr\"\"''\\\\";
			return '"' + e.replace(/([\u0080-\uFFFF\x00-\x1f\"])/g, function(g,
					f) {
				c = a.indexOf(f);
				if (c + 1) {
					return "\\" + a.charAt(c + 1)
				}
				g = f.charCodeAt().toString(16);
				return "\\u" + "0000".substring(g.length) + g
			}) + '"'
		}
		if (b == "object") {
			if (e.hasOwnProperty && e instanceof Array) {
				for (c = 0, a = "["; c < e.length; c++) {
					a += (c > 0 ? "," : "") + d(e[c])
				}
				return a + "]"
			}
			a = "{";
			for (c in e) {
				a += typeof e[c] != "function" ? (a.length > 1 ? ',"' : '"')
						+ c + '":' + d(e[c]) : ""
			}
			return a + "}"
		}
		return "" + e
	},
	parse : function(s) {
		try {
			return eval("(" + s + ")")
		} catch (ex) {
		}
	}
});
tinymce.create("static tinymce.util.XHR", {
	send : function(g) {
		var a, e, b = window, h = 0;
		g.scope = g.scope || this;
		g.success_scope = g.success_scope || g.scope;
		g.error_scope = g.error_scope || g.scope;
		g.async = g.async === false ? false : true;
		g.data = g.data || "";
		function d(i) {
			a = 0;
			try {
				a = new ActiveXObject(i)
			} catch (c) {
			}
			return a
		}
		a = b.XMLHttpRequest ? new XMLHttpRequest() : d("Microsoft.XMLHTTP")
				|| d("Msxml2.XMLHTTP");
		if (a) {
			if (a.overrideMimeType) {
				a.overrideMimeType(g.content_type)
			}
			a.open(g.type || (g.data ? "POST" : "GET"), g.url, g.async);
			if (g.content_type) {
				a.setRequestHeader("Content-Type", g.content_type)
			}
			a.send(g.data);
			function f() {
				if (!g.async || a.readyState == 4 || h++ > 10000) {
					if (g.success && h < 10000 && a.status == 200) {
						g.success.call(g.success_scope, "" + a.responseText, a,
								g)
					} else {
						if (g.error) {
							g.error.call(g.error_scope, h > 10000 ? "TIMED_OUT"
									: "GENERAL", a, g)
						}
					}
					a = null
				} else {
					b.setTimeout(f, 10)
				}
			}
			if (!g.async) {
				return f()
			}
			e = b.setTimeout(f, 10)
		}
	}
});
( function() {
	var c = tinymce.extend, b = tinymce.util.JSON, a = tinymce.util.XHR;
	tinymce.create("tinymce.util.JSONRequest", {
		JSONRequest : function(d) {
			this.settings = c( {}, d);
			this.count = 0
		},
		send : function(f) {
			var e = f.error, d = f.success;
			f = c(this.settings, f);
			f.success = function(h, g) {
				h = b.parse(h);
				if (typeof (h) == "undefined") {
					h = {
						error :"JSON Parse error."
					}
				}
				if (h.error) {
					e.call(f.error_scope || f.scope, h.error, g)
				} else {
					d.call(f.success_scope || f.scope, h.result)
				}
			};
			f.error = function(h, g) {
				e.call(f.error_scope || f.scope, h, g)
			};
			f.data = b.serialize( {
				id :f.id || "c" + (this.count++),
				method :f.method,
				params :f.params
			});
			f.content_type = "application/json";
			a.send(f)
		},
		"static" : {
			sendRPC : function(d) {
				return new tinymce.util.JSONRequest().send(d)
			}
		}
	})
}());
( function(c) {
	var e = c.each, b = c.is;
	var d = c.isWebKit, a = c.isIE;
	c
			.create(
					"tinymce.dom.DOMUtils",
					{
						doc :null,
						root :null,
						files :null,
						pixelStyles :/^(top|left|bottom|right|width|height|borderWidth)$/,
						props : {
							"for" :"htmlFor",
							"class" :"className",
							className :"className",
							checked :"checked",
							disabled :"disabled",
							maxlength :"maxLength",
							readonly :"readOnly",
							selected :"selected",
							value :"value",
							id :"id",
							name :"name",
							type :"type"
						},
						DOMUtils : function(i, g) {
							var f = this;
							f.doc = i;
							f.win = window;
							f.files = {};
							f.cssFlicker = false;
							f.counter = 0;
							f.boxModel = !c.isIE
									|| i.compatMode == "CSS1Compat";
							f.stdMode = i.documentMode === 8;
							this.settings = g = c.extend( {
								keep_values :false,
								hex_colors :1,
								process_html :1
							}, g);
							if (c.isIE6) {
								try {
									i.execCommand("BackgroundImageCache",
											false, true)
								} catch (h) {
									f.cssFlicker = true
								}
							}
							c.addUnload(f.destroy, f)
						},
						getRoot : function() {
							var f = this, g = f.settings;
							return (g && f.get(g.root_element)) || f.doc.body
						},
						getViewPort : function(g) {
							var h, f;
							g = !g ? this.win : g;
							h = g.document;
							f = this.boxModel ? h.documentElement : h.body;
							return {
								x :g.pageXOffset || f.scrollLeft,
								y :g.pageYOffset || f.scrollTop,
								w :g.innerWidth || f.clientWidth,
								h :g.innerHeight || f.clientHeight
							}
						},
						getRect : function(i) {
							var h, f = this, g;
							i = f.get(i);
							h = f.getPos(i);
							g = f.getSize(i);
							return {
								x :h.x,
								y :h.y,
								w :g.w,
								h :g.h
							}
						},
						getSize : function(j) {
							var g = this, f, i;
							j = g.get(j);
							f = g.getStyle(j, "width");
							i = g.getStyle(j, "height");
							if (f.indexOf("px") === -1) {
								f = 0
							}
							if (i.indexOf("px") === -1) {
								i = 0
							}
							return {
								w :parseInt(f) || j.offsetWidth
										|| j.clientWidth,
								h :parseInt(i) || j.offsetHeight
										|| j.clientHeight
							}
						},
						is : function(g, f) {
							return c.dom.Sizzle.matches(f, g.nodeType ? [ g ]
									: g).length > 0
						},
						getParent : function(i, h, g) {
							return this.getParents(i, h, g, false)
						},
						getParents : function(p, k, i, m) {
							var h = this, g, j = h.settings, l = [];
							p = h.get(p);
							m = m === undefined;
							if (j.strict_root) {
								i = i || h.getRoot()
							}
							if (b(k, "string")) {
								g = k;
								if (k === "*") {
									k = function(f) {
										return f.nodeType == 1
									}
								} else {
									k = function(f) {
										return h.is(f, g)
									}
								}
							}
							while (p) {
								if (p == i) {
									break
								}
								if (!k || k(p)) {
									if (m) {
										l.push(p)
									} else {
										return p
									}
								}
								p = p.parentNode
							}
							return m ? l : null
						},
						get : function(f) {
							var g;
							if (f && this.doc && typeof (f) == "string") {
								g = f;
								f = this.doc.getElementById(f);
								if (f && f.id !== g) {
									return this.doc.getElementsByName(g)[1]
								}
							}
							return f
						},
						select : function(h, g) {
							var f = this;
							return c.dom.Sizzle(h, f.get(g)
									|| f.get(f.settings.root_element) || f.doc,
									[])
						},
						add : function(j, l, f, i, k) {
							var g = this;
							return this.run(j,
									function(n) {
										var m, h;
										m = b(l, "string") ? g.doc
												.createElement(l) : l;
										g.setAttribs(m, f);
										if (i) {
											if (i.nodeType) {
												m.appendChild(i)
											} else {
												g.setHTML(m, i)
											}
										}
										return !k ? n.appendChild(m) : m
									})
						},
						create : function(i, f, g) {
							return this.add(this.doc.createElement(i), i, f, g,
									1)
						},
						createHTML : function(m, f, j) {
							var l = "", i = this, g;
							l += "<" + m;
							for (g in f) {
								if (f.hasOwnProperty(g)) {
									l += " " + g + '="' + i.encode(f[g]) + '"'
								}
							}
							if (c.is(j)) {
								return l + ">" + j + "</" + m + ">"
							}
							return l + " />"
						},
						remove : function(h, f) {
							var g = this;
							return this
									.run(
											h,
											function(m) {
												var l, k, j;
												l = m.parentNode;
												if (!l) {
													return null
												}
												if (f) {
													for (j = m.childNodes.length - 1; j >= 0; j--) {
														g
																.insertAfter(
																		m.childNodes[j],
																		m)
													}
												}
												if (g.fixPsuedoLeaks) {
													l = m.cloneNode(true);
													f = "IELeakGarbageBin";
													k = g.get(f)
															|| g
																	.add(
																			g.doc.body,
																			"div",
																			{
																				id :f,
																				style :"display:none"
																			});
													k.appendChild(m);
													k.innerHTML = "";
													return l
												}
												return l.removeChild(m)
											})
						},
						setStyle : function(i, f, g) {
							var h = this;
							return h
									.run(
											i,
											function(l) {
												var k, j;
												k = l.style;
												f = f
														.replace(
																/-(\D)/g,
																function(n, m) {
																	return m
																			.toUpperCase()
																});
												if (h.pixelStyles.test(f)
														&& (c.is(g, "number") || /^[\-0-9\.]+$/
																.test(g))) {
													g += "px"
												}
												switch (f) {
												case "opacity":
													if (a) {
														k.filter = g === "" ? ""
																: "alpha(opacity="
																		+ (g * 100)
																		+ ")";
														if (!i.currentStyle
																|| !i.currentStyle.hasLayout) {
															k.display = "inline-block"
														}
													}
													k[f] = k["-moz-opacity"] = k["-khtml-opacity"] = g
															|| "";
													break;
												case "float":
													a ? k.styleFloat = g
															: k.cssFloat = g;
													break;
												default:
													k[f] = g || ""
												}
												if (h.settings.update_styles) {
													h.setAttrib(l, "mce_style")
												}
											})
						},
						getStyle : function(i, f, h) {
							i = this.get(i);
							if (!i) {
								return false
							}
							if (this.doc.defaultView && h) {
								f = f.replace(/[A-Z]/g, function(j) {
									return "-" + j
								});
								try {
									return this.doc.defaultView
											.getComputedStyle(i, null)
											.getPropertyValue(f)
								} catch (g) {
									return null
								}
							}
							f = f.replace(/-(\D)/g, function(k, j) {
								return j.toUpperCase()
							});
							if (f == "float") {
								f = a ? "styleFloat" : "cssFloat"
							}
							if (i.currentStyle && h) {
								return i.currentStyle[f]
							}
							return i.style[f]
						},
						setStyles : function(i, j) {
							var g = this, h = g.settings, f;
							f = h.update_styles;
							h.update_styles = 0;
							e(j, function(k, l) {
								g.setStyle(i, l, k)
							});
							h.update_styles = f;
							if (h.update_styles) {
								g.setAttrib(i, h.cssText)
							}
						},
						setAttrib : function(h, i, f) {
							var g = this;
							if (!h || !i) {
								return
							}
							if (g.settings.strict) {
								i = i.toLowerCase()
							}
							return this.run(h, function(k) {
								var j = g.settings;
								switch (i) {
								case "style":
									if (!b(f, "string")) {
										e(f, function(l, m) {
											g.setStyle(k, m, l)
										});
										return
									}
									if (j.keep_values) {
										if (f && !g._isRes(f)) {
											k.setAttribute("mce_style", f, 2)
										} else {
											k.removeAttribute("mce_style", 2)
										}
									}
									k.style.cssText = f;
									break;
								case "class":
									k.className = f || "";
									break;
								case "src":
								case "href":
									if (j.keep_values) {
										if (j.url_converter) {
											f = j.url_converter.call(
													j.url_converter_scope || g,
													f, i, k)
										}
										g.setAttrib(k, "mce_" + i, f, 2)
									}
									break;
								case "shape":
									k.setAttribute("mce_style", f);
									break
								}
								if (b(f) && f !== null && f.length !== 0) {
									k.setAttribute(i, "" + f, 2)
								} else {
									k.removeAttribute(i, 2)
								}
							})
						},
						setAttribs : function(g, h) {
							var f = this;
							return this.run(g, function(i) {
								e(h, function(j, k) {
									f.setAttrib(i, k, j)
								})
							})
						},
						getAttrib : function(i, j, h) {
							var f, g = this;
							i = g.get(i);
							if (!i || i.nodeType !== 1) {
								return false
							}
							if (!b(h)) {
								h = ""
							}
							if (/^(src|href|style|coords|shape)$/.test(j)) {
								f = i.getAttribute("mce_" + j);
								if (f) {
									return f
								}
							}
							if (a && g.props[j]) {
								f = i[g.props[j]];
								f = f && f.nodeValue ? f.nodeValue : f
							}
							if (!f) {
								f = i.getAttribute(j, 2)
							}
							if (j === "style") {
								f = f || i.style.cssText;
								if (f) {
									f = g.serializeStyle(g.parseStyle(f));
									if (g.settings.keep_values && !g._isRes(f)) {
										i.setAttribute("mce_style", f)
									}
								}
							}
							if (d && j === "class" && f) {
								f = f.replace(/(apple|webkit)\-[a-z\-]+/gi, "")
							}
							if (a) {
								switch (j) {
								case "rowspan":
								case "colspan":
									if (f === 1) {
										f = ""
									}
									break;
								case "size":
									if (f === "+0" || f === 20 || f === 0) {
										f = ""
									}
									break;
								case "width":
								case "height":
								case "vspace":
								case "checked":
								case "disabled":
								case "readonly":
									if (f === 0) {
										f = ""
									}
									break;
								case "hspace":
									if (f === -1) {
										f = ""
									}
									break;
								case "maxlength":
								case "tabindex":
									if (f === 32768 || f === 2147483647
											|| f === "32768") {
										f = ""
									}
									break;
								case "multiple":
								case "compact":
								case "noshade":
								case "nowrap":
									if (f === 65535) {
										return j
									}
									return h;
								case "shape":
									f = f.toLowerCase();
									break;
								default:
									if (j.indexOf("on") === 0 && f) {
										f = ("" + f)
												.replace(
														/^function\s+anonymous\(\)\s+\{\s+(.*)\s+\}$/,
														"$1")
									}
								}
							}
							return (f !== undefined && f !== null && f !== "") ? ""
									+ f
									: h
						},
						getPos : function(l) {
							var g = this, f = 0, k = 0, i, j = g.doc, h;
							l = g.get(l);
							if (l && a && !g.stdMode) {
								l = l.getBoundingClientRect();
								i = g.boxModel ? j.documentElement : j.body;
								f = g.getStyle(g.select("html")[0],
										"borderWidth");
								f = (f == "medium" || g.boxModel && !g.isIE6)
										&& 2 || f;
								l.top += g.win.self != g.win.top ? 2 : 0;
								return {
									x :l.left + i.scrollLeft - f,
									y :l.top + i.scrollTop - f
								}
							}
							h = l;
							while (h) {
								f += h.offsetLeft || 0;
								k += h.offsetTop || 0;
								h = h.offsetParent
							}
							h = l;
							while (h) {
								if (!/^table-row|inline.*/i.test(g.getStyle(h,
										"display", 1))) {
									f -= h.scrollLeft || 0;
									k -= h.scrollTop || 0
								}
								h = h.parentNode;
								if (!h.nodeType || h.nodeType == 9
										|| h.nodeName.toLowerCase() == "body") {
									break
								}
							}
							return {
								x :f,
								y :k
							}
						},
						parseStyle : function(h) {
							var i = this, j = i.settings, k = {};
							if (!h) {
								return k
							}
							function f(w, q, v) {
								var o, u, m, n;
								o = k[w + "-top" + q];
								if (!o) {
									return
								}
								u = k[w + "-right" + q];
								if (o != u) {
									return
								}
								m = k[w + "-bottom" + q];
								if (u != m) {
									return
								}
								n = k[w + "-left" + q];
								if (m != n) {
									return
								}
								k[v] = n;
								delete k[w + "-top" + q];
								delete k[w + "-right" + q];
								delete k[w + "-bottom" + q];
								delete k[w + "-left" + q]
							}
							function g(n, m, l, p) {
								var o;
								o = k[m];
								if (!o) {
									return
								}
								o = k[l];
								if (!o) {
									return
								}
								o = k[p];
								if (!o) {
									return
								}
								k[n] = k[m] + " " + k[l] + " " + k[p];
								delete k[m];
								delete k[l];
								delete k[p]
							}
							h = h.replace(/&(#?[a-z0-9]+);/g, "&$1_MCE_SEMI_");
							e(
									h.split(";"),
									function(m) {
										var l, n = [];
										if (m) {
											m = m.replace(/_MCE_SEMI_/g, ";");
											m = m.replace(/url\([^\)]+\)/g,
													function(o) {
														n.push(o);
														return "url("
																+ n.length
																+ ")"
													});
											m = m.split(":");
											l = c.trim(m[1]);
											l = l
													.replace(
															/url\(([^\)]+)\)/g,
															function(p, o) {
																return n[parseInt(o) - 1]
															});
											l = l.replace(/rgb\([^\)]+\)/g,
													function(o) {
														return i.toHex(o)
													});
											if (j.url_converter) {
												l = l
														.replace(
																/url\([\'\"]?([^\)\'\"]+)[\'\"]?\)/g,
																function(o, p) {
																	return "url("
																			+ j.url_converter
																					.call(
																							j.url_converter_scope
																									|| i,
																							i
																									.decode(p),
																							"style",
																							null)
																			+ ")"
																})
											}
											k[c.trim(m[0]).toLowerCase()] = l
										}
									});
							f("border", "", "border");
							f("border", "-width", "border-width");
							f("border", "-color", "border-color");
							f("border", "-style", "border-style");
							f("padding", "", "padding");
							f("margin", "", "margin");
							g("border", "border-width", "border-style",
									"border-color");
							if (a) {
								if (k.border == "medium none") {
									k.border = ""
								}
							}
							return k
						},
						serializeStyle : function(g) {
							var f = "";
							e(g, function(i, h) {
								if (h && i) {
									if (c.isGecko && h.indexOf("-moz-") === 0) {
										return
									}
									switch (h) {
									case "color":
									case "background-color":
										i = i.toLowerCase();
										break
									}
									f += (f ? " " : "") + h + ": " + i + ";"
								}
							});
							return f
						},
						loadCSS : function(f) {
							var g = this, h = g.doc;
							if (!f) {
								f = ""
							}
							e(f.split(","), function(i) {
								if (g.files[i]) {
									return
								}
								g.files[i] = true;
								g.add(g.select("head")[0], "link", {
									rel :"stylesheet",
									href :c._addVer(i)
								})
							})
						},
						addClass : function(f, g) {
							return this.run(f, function(h) {
								var i;
								if (!g) {
									return 0
								}
								if (this.hasClass(h, g)) {
									return h.className
								}
								i = this.removeClass(h, g);
								return h.className = (i != "" ? (i + " ") : "")
										+ g
							})
						},
						removeClass : function(h, i) {
							var f = this, g;
							return f.run(h, function(k) {
								var j;
								if (f.hasClass(k, i)) {
									if (!g) {
										g = new RegExp("(^|\\s+)" + i
												+ "(\\s+|$)", "g")
									}
									j = k.className.replace(g, " ");
									return k.className = c.trim(j != " " ? j
											: "")
								}
								return k.className
							})
						},
						hasClass : function(g, f) {
							g = this.get(g);
							if (!g || !f) {
								return false
							}
							return (" " + g.className + " ").indexOf(" " + f
									+ " ") !== -1
						},
						show : function(f) {
							return this.setStyle(f, "display", "block")
						},
						hide : function(f) {
							return this.setStyle(f, "display", "none")
						},
						isHidden : function(f) {
							f = this.get(f);
							return !f || f.style.display == "none"
									|| this.getStyle(f, "display") == "none"
						},
						uniqueId : function(f) {
							return (!f ? "mce_" : f) + (this.counter++)
						},
						setHTML : function(i, g) {
							var f = this;
							return this
									.run(
											i,
											function(m) {
												var h, k, j, q, l, h;
												g = f.processHTML(g);
												if (a) {
													function o() {
														try {
															m.innerHTML = "<br />"
																	+ g;
															m
																	.removeChild(m.firstChild)
														} catch (n) {
															while (m.firstChild) {
																m.firstChild
																		.removeNode()
															}
															h = f.create("div");
															h.innerHTML = "<br />"
																	+ g;
															e(
																	h.childNodes,
																	function(r,
																			p) {
																		if (p) {
																			m
																					.appendChild(r)
																		}
																	})
														}
													}
													if (f.settings.fix_ie_paragraphs) {
														g = g
																.replace(
																		/<p><\/p>|<p([^>]+)><\/p>|<p[^\/+]\/>/gi,
																		'<p$1 mce_keep="true">&nbsp;</p>')
													}
													o();
													if (f.settings.fix_ie_paragraphs) {
														j = m
																.getElementsByTagName("p");
														for (k = j.length - 1, h = 0; k >= 0; k--) {
															q = j[k];
															if (!q
																	.hasChildNodes()) {
																if (!q.mce_keep) {
																	h = 1;
																	break
																}
																q
																		.removeAttribute("mce_keep")
															}
														}
													}
													if (h) {
														g = g
																.replace(
																		/<p ([^>]+)>|<p>/g,
																		'<div $1 mce_tmp="1">');
														g = g.replace(/<\/p>/g,
																"</div>");
														o();
														if (f.settings.fix_ie_paragraphs) {
															j = m
																	.getElementsByTagName("DIV");
															for (k = j.length - 1; k >= 0; k--) {
																q = j[k];
																if (q.mce_tmp) {
																	l = f.doc
																			.createElement("p");
																	q
																			.cloneNode(false).outerHTML
																			.replace(
																					/([a-z0-9\-_]+)=/gi,
																					function(
																							p,
																							n) {
																						var r;
																						if (n !== "mce_tmp") {
																							r = q
																									.getAttribute(n);
																							if (!r
																									&& n === "class") {
																								r = q.className
																							}
																							l
																									.setAttribute(
																											n,
																											r)
																						}
																					});
																	for (h = 0; h < q.childNodes.length; h++) {
																		l
																				.appendChild(q.childNodes[h]
																						.cloneNode(true))
																	}
																	q
																			.swapNode(l)
																}
															}
														}
													}
												} else {
													m.innerHTML = g
												}
												return g
											})
						},
						processHTML : function(j) {
							var g = this, i = g.settings;
							if (!i.process_html) {
								return j
							}
							if (c.isGecko) {
								j = j.replace(
										/<(\/?)strong>|<strong( [^>]+)>/gi,
										"<$1b$2>");
								j = j.replace(/<(\/?)em>|<em( [^>]+)>/gi,
										"<$1i$2>")
							} else {
								if (a) {
									j = j.replace(/&apos;/g, "&#39;");
									j = j
											.replace(
													/\s+(disabled|checked|readonly|selected)\s*=\s*[\"\']?(false|0)[\"\']?/gi,
													"")
								}
							}
							j = j.replace(/<a( )([^>]+)\/>|<a\/>/gi,
									"<a$1$2></a>");
							if (i.keep_values) {
								if (/<script|style/.test(j)) {
									function f(h) {
										h = h.replace(
												/(<!--\[CDATA\[|\]\]-->)/g,
												"\n");
										h = h.replace(/^[\r\n]*|[\r\n]*$/g, "");
										h = h
												.replace(
														/^\s*(\/\/\s*<!--|\/\/\s*<!\[CDATA\[|<!--|<!\[CDATA\[)[\r\n]*/g,
														"");
										h = h
												.replace(
														/\s*(\/\/\s*\]\]>|\/\/\s*-->|\]\]>|-->|\]\]-->)\s*$/g,
														"");
										return h
									}
									j = j
											.replace(
													/<script([^>]+|)>([\s\S]*?)<\/script>/g,
													function(l, k, h) {
														h = f(h);
														if (!k) {
															k = ' type="text/javascript"'
														}
														if (h) {
															h = "<!--\n"
																	+ h
																	+ "\n// -->"
														}
														return "<mce:script"
																+ k
																+ ">"
																+ h
																+ "</mce:script>"
													});
									j = j
											.replace(
													/<style([^>]+|)>([\s\S]*?)<\/style>/g,
													function(l, k, h) {
														h = f(h);
														return "<mce:style"
																+ k
																+ "><!--\n"
																+ h
																+ "\n--></mce:style><style"
																+ k
																+ ' mce_bogus="1">'
																+ h
																+ "</style>"
													})
								}
								j = j.replace(/<!\[CDATA\[([\s\S]+)\]\]>/g,
										"<!--[CDATA[$1]]-->");
								j = j
										.replace(
												/<([\w:]+) [^>]*(src|href|style|shape|coords)[^>]*>/gi,
												function(h, l) {
													function k(o, n, q) {
														var p = q;
														if (h.indexOf("mce_"
																+ n) != -1) {
															return o
														}
														if (n == "style") {
															if (g._isRes(q)) {
																return o
															}
															if (i.hex_colors) {
																p = p
																		.replace(
																				/rgb\([^\)]+\)/g,
																				function(
																						m) {
																					return g
																							.toHex(m)
																				})
															}
															if (i.url_converter) {
																p = p
																		.replace(
																				/url\([\'\"]?([^\)\'\"]+)\)/g,
																				function(
																						m,
																						r) {
																					return "url("
																							+ g
																									.encode(i.url_converter
																											.call(
																													i.url_converter_scope
																															|| g,
																													g
																															.decode(r),
																													n,
																													l))
																							+ ")"
																				})
															}
														} else {
															if (n != "coords"
																	&& n != "shape") {
																if (i.url_converter) {
																	p = g
																			.encode(i.url_converter
																					.call(
																							i.url_converter_scope
																									|| g,
																							g
																									.decode(q),
																							n,
																							l))
																}
															}
														}
														return " " + n + '="'
																+ q + '" mce_'
																+ n + '="' + p
																+ '"'
													}
													h = h
															.replace(
																	/ (src|href|style|coords|shape)=[\"]([^\"]+)[\"]/gi,
																	k);
													h = h
															.replace(
																	/ (src|href|style|coords|shape)=[\']([^\']+)[\']/gi,
																	k);
													return h
															.replace(
																	/ (src|href|style|coords|shape)=([^\s\"\'>]+)/gi,
																	k)
												})
							}
							return j
						},
						getOuterHTML : function(f) {
							var g;
							f = this.get(f);
							if (!f) {
								return null
							}
							if (f.outerHTML !== undefined) {
								return f.outerHTML
							}
							g = (f.ownerDocument || this.doc)
									.createElement("body");
							g.appendChild(f.cloneNode(true));
							return g.innerHTML
						},
						setOuterHTML : function(i, g, j) {
							var f = this;
							return this.run(i, function(h) {
								var l, k;
								h = f.get(h);
								j = j || h.ownerDocument || f.doc;
								if (a && h.nodeType == 1) {
									h.outerHTML = g
								} else {
									k = j.createElement("body");
									k.innerHTML = g;
									l = k.lastChild;
									while (l) {
										f.insertAfter(l.cloneNode(true), h);
										l = l.previousSibling
									}
									f.remove(h)
								}
							})
						},
						decode : function(g) {
							var h, i, f;
							if (/&[^;]+;/.test(g)) {
								h = this.doc.createElement("div");
								h.innerHTML = g;
								i = h.firstChild;
								f = "";
								if (i) {
									do {
										f += i.nodeValue
									} while (i.nextSibling)
								}
								return f || g
							}
							return g
						},
						encode : function(f) {
							return f ? ("" + f).replace(/[<>&\"]/g, function(h,
									g) {
								switch (h) {
								case "&":
									return "&amp;";
								case '"':
									return "&quot;";
								case "<":
									return "&lt;";
								case ">":
									return "&gt;"
								}
								return h
							}) : f
						},
						insertAfter : function(h, g) {
							var f = this;
							g = f.get(g);
							return this.run(h, function(k) {
								var j, i;
								j = g.parentNode;
								i = g.nextSibling;
								if (i) {
									j.insertBefore(k, i)
								} else {
									j.appendChild(k)
								}
								return k
							})
						},
						isBlock : function(f) {
							if (f.nodeType && f.nodeType !== 1) {
								return false
							}
							f = f.nodeName || f;
							return /^(H[1-6]|HR|P|DIV|ADDRESS|PRE|FORM|TABLE|LI|OL|UL|TR|TD|CAPTION|BLOCKQUOTE|CENTER|DL|DT|DD|DIR|FIELDSET|NOSCRIPT|NOFRAMES|MENU|ISINDEX|SAMP)$/
									.test(f)
						},
						replace : function(i, h, f) {
							var g = this;
							if (b(h, "array")) {
								i = i.cloneNode(true)
							}
							return g.run(h, function(j) {
								if (f) {
									e(j.childNodes, function(k) {
										i.appendChild(k.cloneNode(true))
									})
								}
								if (g.fixPsuedoLeaks && j.nodeType === 1) {
									j.parentNode.insertBefore(i, j);
									g.remove(j);
									return i
								}
								return j.parentNode.replaceChild(i, j)
							})
						},
						findCommonAncestor : function(h, f) {
							var i = h, g;
							while (i) {
								g = f;
								while (g && i != g) {
									g = g.parentNode
								}
								if (i == g) {
									break
								}
								i = i.parentNode
							}
							if (!i && h.ownerDocument) {
								return h.ownerDocument.documentElement
							}
							return i
						},
						toHex : function(f) {
							var h = /^\s*rgb\s*?\(\s*?([0-9]+)\s*?,\s*?([0-9]+)\s*?,\s*?([0-9]+)\s*?\)\s*$/i
									.exec(f);
							function g(i) {
								i = parseInt(i).toString(16);
								return i.length > 1 ? i : "0" + i
							}
							if (h) {
								f = "#" + g(h[1]) + g(h[2]) + g(h[3]);
								return f
							}
							return f
						},
						getClasses : function() {
							var l = this, g = [], k, m = {}, n = l.settings.class_filter, j;
							if (l.classes) {
								return l.classes
							}
							function o(f) {
								e(f.imports, function(i) {
									o(i)
								});
								e(
										f.cssRules || f.rules,
										function(i) {
											switch (i.type || 1) {
											case 1:
												if (i.selectorText) {
													e(
															i.selectorText
																	.split(","),
															function(p) {
																p = p
																		.replace(
																				/^\s*|\s*$|^\s\./g,
																				"");
																if (/\.mce/
																		.test(p)
																		|| !/\.[\w\-]+$/
																				.test(p)) {
																	return
																}
																j = p;
																p = p
																		.replace(
																				/.*\.([a-z0-9_\-]+).*/i,
																				"$1");
																if (n
																		&& !(p = n(
																				p,
																				j))) {
																	return
																}
																if (!m[p]) {
																	g
																			.push( {
																				"class" :p
																			});
																	m[p] = 1
																}
															})
												}
												break;
											case 3:
												o(i.styleSheet);
												break
											}
										})
							}
							try {
								e(l.doc.styleSheets, o)
							} catch (h) {
							}
							if (g.length > 0) {
								l.classes = g
							}
							return g
						},
						run : function(j, i, h) {
							var g = this, k;
							if (g.doc && typeof (j) === "string") {
								j = g.get(j)
							}
							if (!j) {
								return false
							}
							h = h || this;
							if (!j.nodeType && (j.length || j.length === 0)) {
								k = [];
								e(j, function(l, f) {
									if (l) {
										if (typeof (l) == "string") {
											l = g.doc.getElementById(l)
										}
										k.push(i.call(h, l, f))
									}
								});
								return k
							}
							return i.call(h, j)
						},
						getAttribs : function(g) {
							var f;
							g = this.get(g);
							if (!g) {
								return []
							}
							if (a) {
								f = [];
								if (g.nodeName == "OBJECT") {
									return g.attributes
								}
								g.cloneNode(false).outerHTML.replace(
										/([a-z0-9\:\-_]+)=/gi, function(i, h) {
											f.push( {
												specified :1,
												nodeName :h
											})
										});
								return f
							}
							return g.attributes
						},
						destroy : function(g) {
							var f = this;
							f.win = f.doc = f.root = null;
							if (!g) {
								c.removeUnload(f.destroy)
							}
						},
						createRng : function() {
							var f = this.doc;
							return f.createRange ? f.createRange()
									: new c.dom.Range(this)
						},
						split : function(k, j, n) {
							var o = this, f = o.createRng(), l, i, m;
							function g(q, p) {
								q = q[p];
								if (q && q[p] && q[p].nodeType == 1 && h(q[p])) {
									o.remove(q[p])
								}
							}
							function h(p) {
								p = o.getOuterHTML(p);
								p = p.replace(/<(img|hr|table)/gi, "-");
								p = p.replace(/<[^>]+>/g, "");
								return p.replace(/[ \t\r\n]+|&nbsp;|&#160;/g,
										"") == ""
							}
							if (k && j) {
								f.setStartBefore(k);
								f.setEndBefore(j);
								l = f.extractContents();
								f = o.createRng();
								f.setStartAfter(j);
								f.setEndAfter(k);
								i = f.extractContents();
								m = k.parentNode;
								g(l, "lastChild");
								if (!h(l)) {
									m.insertBefore(l, k)
								}
								if (n) {
									m.replaceChild(n, j)
								} else {
									m.insertBefore(j, k)
								}
								g(i, "firstChild");
								if (!h(i)) {
									m.insertBefore(i, k)
								}
								o.remove(k);
								return n || j
							}
						},
						_isRes : function(f) {
							return /^(top|left|bottom|right|width|height)/i
									.test(f)
									|| /;\s*(top|left|bottom|right|width|height)/i
											.test(f)
						}
					});
	c.DOM = new c.dom.DOMUtils(document, {
		process_html :0
	})
})(tinymce);
( function(f) {
	var h = 0, c = 1, e = 2, d = tinymce.extend;
	function g(m, k) {
		var j, l;
		if (m.parentNode != k) {
			return -1
		}
		for (l = k.firstChild, j = 0; l != m; l = l.nextSibling) {
			j++
		}
		return j
	}
	function b(k) {
		var j = 0;
		while (k.previousSibling) {
			j++;
			k = k.previousSibling
		}
		return j
	}
	function i(j, k) {
		var l;
		if (j.nodeType == 3) {
			return j
		}
		if (k < 0) {
			return j
		}
		l = j.firstChild;
		while (l != null && k > 0) {
			--k;
			l = l.nextSibling
		}
		if (l != null) {
			return l
		}
		return j
	}
	function a(k) {
		var j = k.doc;
		d(this, {
			dom :k,
			startContainer :j,
			startOffset :0,
			endContainer :j,
			endOffset :0,
			collapsed :true,
			commonAncestorContainer :j,
			START_TO_START :0,
			START_TO_END :1,
			END_TO_END :2,
			END_TO_START :3
		})
	}
	d(
			a.prototype,
			{
				setStart : function(k, j) {
					this._setEndPoint(true, k, j)
				},
				setEnd : function(k, j) {
					this._setEndPoint(false, k, j)
				},
				setStartBefore : function(j) {
					this.setStart(j.parentNode, b(j))
				},
				setStartAfter : function(j) {
					this.setStart(j.parentNode, b(j) + 1)
				},
				setEndBefore : function(j) {
					this.setEnd(j.parentNode, b(j))
				},
				setEndAfter : function(j) {
					this.setEnd(j.parentNode, b(j) + 1)
				},
				collapse : function(k) {
					var j = this;
					if (k) {
						j.endContainer = j.startContainer;
						j.endOffset = j.startOffset
					} else {
						j.startContainer = j.endContainer;
						j.startOffset = j.endOffset
					}
					j.collapsed = true
				},
				selectNode : function(j) {
					this.setStartBefore(j);
					this.setEndAfter(j)
				},
				selectNodeContents : function(j) {
					this.setStart(j, 0);
					this.setEnd(j, j.nodeType === 1 ? j.childNodes.length
							: j.nodeValue.length)
				},
				compareBoundaryPoints : function(m, n) {
					var l = this, p = l.startContainer, o = l.startOffset, k = l.endContainer, j = l.endOffset;
					if (m === 0) {
						return l._compareBoundaryPoints(p, o, p, o)
					}
					if (m === 1) {
						return l._compareBoundaryPoints(p, o, k, j)
					}
					if (m === 2) {
						return l._compareBoundaryPoints(k, j, k, j)
					}
					if (m === 3) {
						return l._compareBoundaryPoints(k, j, p, o)
					}
				},
				deleteContents : function() {
					this._traverse(e)
				},
				extractContents : function() {
					return this._traverse(h)
				},
				cloneContents : function() {
					return this._traverse(c)
				},
				insertNode : function(m) {
					var j = this, l, k;
					if (m.nodeType === 3 || m.nodeType === 4) {
						l = j.startContainer.splitText(j.startOffset);
						j.startContainer.parentNode.insertBefore(m, l)
					} else {
						if (j.startContainer.childNodes.length > 0) {
							k = j.startContainer.childNodes[j.startOffset]
						}
						j.startContainer.insertBefore(m, k)
					}
				},
				surroundContents : function(l) {
					var j = this, k = j.extractContents();
					j.insertNode(l);
					l.appendChild(k);
					j.selectNode(l)
				},
				cloneRange : function() {
					var j = this;
					return d(new a(j.dom), {
						startContainer :j.startContainer,
						startOffset :j.startOffset,
						endContainer :j.endContainer,
						endOffset :j.endOffset,
						collapsed :j.collapsed,
						commonAncestorContainer :j.commonAncestorContainer
					})
				},
				_isCollapsed : function() {
					return (this.startContainer == this.endContainer && this.startOffset == this.endOffset)
				},
				_compareBoundaryPoints : function(m, p, k, o) {
					var q, l, j, r, t, s;
					if (m == k) {
						if (p == o) {
							return 0
						} else {
							if (p < o) {
								return -1
							} else {
								return 1
							}
						}
					}
					q = k;
					while (q && q.parentNode != m) {
						q = q.parentNode
					}
					if (q) {
						l = 0;
						j = m.firstChild;
						while (j != q && l < p) {
							l++;
							j = j.nextSibling
						}
						if (p <= l) {
							return -1
						} else {
							return 1
						}
					}
					q = m;
					while (q && q.parentNode != k) {
						q = q.parentNode
					}
					if (q) {
						l = 0;
						j = k.firstChild;
						while (j != q && l < o) {
							l++;
							j = j.nextSibling
						}
						if (l < o) {
							return -1
						} else {
							return 1
						}
					}
					r = this.dom.findCommonAncestor(m, k);
					t = m;
					while (t && t.parentNode != r) {
						t = t.parentNode
					}
					if (!t) {
						t = r
					}
					s = k;
					while (s && s.parentNode != r) {
						s = s.parentNode
					}
					if (!s) {
						s = r
					}
					if (t == s) {
						return 0
					}
					j = r.firstChild;
					while (j) {
						if (j == t) {
							return -1
						}
						if (j == s) {
							return 1
						}
						j = j.nextSibling
					}
				},
				_setEndPoint : function(k, q, p) {
					var l = this, j, m;
					if (k) {
						l.startContainer = q;
						l.startOffset = p
					} else {
						l.endContainer = q;
						l.endOffset = p
					}
					j = l.endContainer;
					while (j.parentNode) {
						j = j.parentNode
					}
					m = l.startContainer;
					while (m.parentNode) {
						m = m.parentNode
					}
					if (m != j) {
						l.collapse(k)
					} else {
						if (l._compareBoundaryPoints(l.startContainer,
								l.startOffset, l.endContainer, l.endOffset) > 0) {
							l.collapse(k)
						}
					}
					l.collapsed = l._isCollapsed();
					l.commonAncestorContainer = l.dom.findCommonAncestor(
							l.startContainer, l.endContainer)
				},
				_traverse : function(r) {
					var s = this, q, m = 0, v = 0, k, o, l, n, j, u;
					if (s.startContainer == s.endContainer) {
						return s._traverseSameContainer(r)
					}
					for (q = s.endContainer, k = q.parentNode; k != null; q = k, k = k.parentNode) {
						if (k == s.startContainer) {
							return s._traverseCommonStartContainer(q, r)
						}
						++m
					}
					for (q = s.startContainer, k = q.parentNode; k != null; q = k, k = k.parentNode) {
						if (k == s.endContainer) {
							return s._traverseCommonEndContainer(q, r)
						}
						++v
					}
					o = v - m;
					l = s.startContainer;
					while (o > 0) {
						l = l.parentNode;
						o--
					}
					n = s.endContainer;
					while (o < 0) {
						n = n.parentNode;
						o++
					}
					for (j = l.parentNode, u = n.parentNode; j != u; j = j.parentNode, u = u.parentNode) {
						l = j;
						n = u
					}
					return s._traverseCommonAncestors(l, n, r)
				},
				_traverseSameContainer : function(o) {
					var r = this, q, u, j, k, l, p, m;
					if (o != e) {
						q = r.dom.doc.createDocumentFragment()
					}
					if (r.startOffset == r.endOffset) {
						return q
					}
					if (r.startContainer.nodeType == 3) {
						u = r.startContainer.nodeValue;
						j = u.substring(r.startOffset, r.endOffset);
						if (o != c) {
							r.startContainer.deleteData(r.startOffset,
									r.endOffset - r.startOffset);
							r.collapse(true)
						}
						if (o == e) {
							return null
						}
						q.appendChild(r.dom.doc.createTextNode(j));
						return q
					}
					k = i(r.startContainer, r.startOffset);
					l = r.endOffset - r.startOffset;
					while (l > 0) {
						p = k.nextSibling;
						m = r._traverseFullySelected(k, o);
						if (q) {
							q.appendChild(m)
						}
						--l;
						k = p
					}
					if (o != c) {
						r.collapse(true)
					}
					return q
				},
				_traverseCommonStartContainer : function(j, p) {
					var s = this, r, k, l, m, q, o;
					if (p != e) {
						r = s.dom.doc.createDocumentFragment()
					}
					k = s._traverseRightBoundary(j, p);
					if (r) {
						r.appendChild(k)
					}
					l = g(j, s.startContainer);
					m = l - s.startOffset;
					if (m <= 0) {
						if (p != c) {
							s.setEndBefore(j);
							s.collapse(false)
						}
						return r
					}
					k = j.previousSibling;
					while (m > 0) {
						q = k.previousSibling;
						o = s._traverseFullySelected(k, p);
						if (r) {
							r.insertBefore(o, r.firstChild)
						}
						--m;
						k = q
					}
					if (p != c) {
						s.setEndBefore(j);
						s.collapse(false)
					}
					return r
				},
				_traverseCommonEndContainer : function(m, p) {
					var s = this, r, o, j, k, q, l;
					if (p != e) {
						r = s.dom.doc.createDocumentFragment()
					}
					j = s._traverseLeftBoundary(m, p);
					if (r) {
						r.appendChild(j)
					}
					o = g(m, s.endContainer);
					++o;
					k = s.endOffset - o;
					j = m.nextSibling;
					while (k > 0) {
						q = j.nextSibling;
						l = s._traverseFullySelected(j, p);
						if (r) {
							r.appendChild(l)
						}
						--k;
						j = q
					}
					if (p != c) {
						s.setStartAfter(m);
						s.collapse(true)
					}
					return r
				},
				_traverseCommonAncestors : function(p, j, s) {
					var w = this, l, v, o, q, r, k, u, m;
					if (s != e) {
						v = w.dom.doc.createDocumentFragment()
					}
					l = w._traverseLeftBoundary(p, s);
					if (v) {
						v.appendChild(l)
					}
					o = p.parentNode;
					q = g(p, o);
					r = g(j, o);
					++q;
					k = r - q;
					u = p.nextSibling;
					while (k > 0) {
						m = u.nextSibling;
						l = w._traverseFullySelected(u, s);
						if (v) {
							v.appendChild(l)
						}
						u = m;
						--k
					}
					l = w._traverseRightBoundary(j, s);
					if (v) {
						v.appendChild(l)
					}
					if (s != c) {
						w.setStartAfter(p);
						w.collapse(true)
					}
					return v
				},
				_traverseRightBoundary : function(p, q) {
					var s = this, l = i(s.endContainer, s.endOffset - 1), r, o, n, j, k;
					var m = l != s.endContainer;
					if (l == p) {
						return s._traverseNode(l, m, false, q)
					}
					r = l.parentNode;
					o = s._traverseNode(r, false, false, q);
					while (r != null) {
						while (l != null) {
							n = l.previousSibling;
							j = s._traverseNode(l, m, false, q);
							if (q != e) {
								o.insertBefore(j, o.firstChild)
							}
							m = true;
							l = n
						}
						if (r == p) {
							return o
						}
						l = r.previousSibling;
						r = r.parentNode;
						k = s._traverseNode(r, false, false, q);
						if (q != e) {
							k.appendChild(o)
						}
						o = k
					}
					return null
				},
				_traverseLeftBoundary : function(p, q) {
					var s = this, m = i(s.startContainer, s.startOffset);
					var n = m != s.startContainer, r, o, l, j, k;
					if (m == p) {
						return s._traverseNode(m, n, true, q)
					}
					r = m.parentNode;
					o = s._traverseNode(r, false, true, q);
					while (r != null) {
						while (m != null) {
							l = m.nextSibling;
							j = s._traverseNode(m, n, true, q);
							if (q != e) {
								o.appendChild(j)
							}
							n = true;
							m = l
						}
						if (r == p) {
							return o
						}
						m = r.nextSibling;
						r = r.parentNode;
						k = s._traverseNode(r, false, true, q);
						if (q != e) {
							k.appendChild(o)
						}
						o = k
					}
					return null
				},
				_traverseNode : function(j, o, r, s) {
					var u = this, m, l, p, k, q;
					if (o) {
						return u._traverseFullySelected(j, s)
					}
					if (j.nodeType == 3) {
						m = j.nodeValue;
						if (r) {
							k = u.startOffset;
							l = m.substring(k);
							p = m.substring(0, k)
						} else {
							k = u.endOffset;
							l = m.substring(0, k);
							p = m.substring(k)
						}
						if (s != c) {
							j.nodeValue = p
						}
						if (s == e) {
							return null
						}
						q = j.cloneNode(false);
						q.nodeValue = l;
						return q
					}
					if (s == e) {
						return null
					}
					return j.cloneNode(false)
				},
				_traverseFullySelected : function(l, k) {
					var j = this;
					if (k != e) {
						return k == c ? l.cloneNode(true) : l
					}
					l.parentNode.removeChild(l);
					return null
				}
			});
	f.Range = a
})(tinymce.dom);
( function() {
	function a(c) {
		var b = this;
		function d() {
			var k = c.dom, j = c.getRng(), e = k.createRng(), g = {}, f = {};
			if (j.item) {
				e.setStartBefore(j.item(0));
				e.setEndAfter(j.item(0));
				return e
			}
			function h(q, p, o) {
				var m, l, n;
				m = q.duplicate();
				m.collapse(p);
				element = m.parentElement();
				if (element.currentStyle.display == "block") {
					m = q.duplicate();
					l = q.duplicate();
					if (p) {
						m.moveStart("character", 1)
					} else {
						m.moveEnd("character", -1)
					}
					if (m.text != l.text) {
						m = l
					}
					m.collapse(p);
					element = m.parentElement()
				}
				o.parent = element;
				o.range = m
			}
			function i(r) {
				var o = r.range, p, n, m, q, l = 0;
				r.offset = 0;
				r.parent = o.parentElement();
				o.pasteHTML('<span id="_mce"></span>');
				m = k.get("_mce");
				n = r.parent.childNodes;
				for (p = 0; p < n.length; p++) {
					if (n[p] == m) {
						r.index = l;
						break
					}
					if (p > 0 && (n[p].nodeType != 3 || n[p - 1].nodeType != 3)) {
						l++
					}
				}
				q = m.previousSibling;
				if (q) {
					if (q.nodeType === 3) {
						do {
							r.offset += q.nodeValue.length
						} while ((q = q.previousSibling) && q.nodeType == 3)
					} else {
						r.index++
					}
				}
				k.remove(m);
				return r
			}
			h(j, true, g);
			h(j, false, f);
			i(g);
			i(f);
			g.parent.normalize();
			f.parent.normalize();
			e.setStart(g.parent.childNodes[g.index], g.offset);
			e.setEnd(f.parent.childNodes[f.index], f.offset);
			b.addRange(e);
			return e
		}
		this.addRange = function(g) {
			var j, i, h, e = c.dom.doc.body;
			if (g.startContainer.nodeType == 1) {
				j = e.createControlRange();
				j.addElement(g.startContainer.childNodes[g.startOffset]);
				return
			}
			function f(p) {
				var m, n, l, o;
				m = p ? g.startContainer : g.endContainer;
				n = p ? g.startOffset : g.endOffset;
				m.nodeValue = m.nodeValue.substring(0, n) + "\uFEFF"
						+ m.nodeValue.substring(n);
				l = e.createTextRange();
				l.moveToElementText(m.parentNode);
				o = l.text.indexOf("\uFEFF");
				m.nodeValue = m.nodeValue.replace(/\uFEFF/, "");
				if (p) {
					i = o
				} else {
					h = o
				}
			}
			function k(n) {
				var l, m = n ? g.startContainer : g.endContainer;
				l = e.createTextRange();
				l.moveToElementText(m.parentNode);
				l.collapse(true);
				l.move("character", n ? i : h);
				if (n) {
					j.setEndPoint("StartToStart", l)
				} else {
					j.setEndPoint("EndToStart", l)
				}
			}
			j = e.createTextRange();
			f(true);
			f(false);
			k(true);
			k(false);
			j.select()
		};
		this.getRangeAt = function() {
			return d()
		}
	}
	tinymce.dom.TridentSelection = a
})();
( function() {
	var l = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^[\]]*\]|['"][^'"]*['"]|[^[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?/g, m = 0, o = Object.prototype.toString, f = Array.prototype.splice, r = Array.prototype.push, g = Array.prototype.sort;
	var b = function(F, v, C, x) {
		C = C || [];
		var e = v = v || document;
		if (v.nodeType !== 1 && v.nodeType !== 9) {
			return []
		}
		if (!F || typeof F !== "string") {
			return C
		}
		var D = [], E, A, I, H, B, u, t = true, y = c(v);
		l.lastIndex = 0;
		while ((E = l.exec(F)) !== null) {
			D.push(E[1]);
			if (E[2]) {
				u = RegExp.rightContext;
				break
			}
		}
		if (D.length > 1 && h.exec(F)) {
			if (D.length === 2 && i.relative[D[0]]) {
				A = p(D[0] + D[1], v)
			} else {
				A = i.relative[D[0]] ? [ v ] : b(D.shift(), v);
				while (D.length) {
					F = D.shift();
					if (i.relative[F]) {
						F += D.shift()
					}
					A = p(F, A)
				}
			}
		} else {
			if (!x && D.length > 1 && v.nodeType === 9 && !y
					&& i.match.ID.test(D[0])
					&& !i.match.ID.test(D[D.length - 1])) {
				var J = b.find(D.shift(), v, y);
				v = J.expr ? b.filter(J.expr, J.set)[0] : J.set[0]
			}
			if (v) {
				var J = x ? {
					expr :D.pop(),
					set :k(x)
				} : b.find(D.pop(),
						D.length === 1 && (D[0] === "~" || D[0] === "+")
								&& v.parentNode ? v.parentNode : v, y);
				A = J.expr ? b.filter(J.expr, J.set) : J.set;
				if (D.length > 0) {
					I = k(A)
				} else {
					t = false
				}
				while (D.length) {
					var w = D.pop(), z = w;
					if (!i.relative[w]) {
						w = ""
					} else {
						z = D.pop()
					}
					if (z == null) {
						z = v
					}
					i.relative[w](I, z, y)
				}
			} else {
				I = D = []
			}
		}
		if (!I) {
			I = A
		}
		if (!I) {
			throw "Syntax error, unrecognized expression: " + (w || F)
		}
		if (o.call(I) === "[object Array]") {
			if (!t) {
				r.apply(C, I)
			} else {
				if (v && v.nodeType === 1) {
					for ( var G = 0; I[G] != null; G++) {
						if (I[G]
								&& (I[G] === true || I[G].nodeType === 1
										&& j(v, I[G]))) {
							r.call(C, A[G])
						}
					}
				} else {
					for ( var G = 0; I[G] != null; G++) {
						if (I[G] && I[G].nodeType === 1) {
							r.call(C, A[G])
						}
					}
				}
			}
		} else {
			k(I, C)
		}
		if (u) {
			b(u, e, C, x);
			b.uniqueSort(C)
		}
		return C
	};
	b.uniqueSort = function(t) {
		if (n) {
			hasDuplicate = false;
			g.call(t, n);
			if (hasDuplicate) {
				for ( var e = 1; e < t.length; e++) {
					if (t[e] === t[e - 1]) {
						f.call(t, e--, 1)
					}
				}
			}
		}
	};
	b.matches = function(e, t) {
		return b(e, null, null, t)
	};
	b.find = function(z, e, A) {
		var y, w;
		if (!z) {
			return []
		}
		for ( var v = 0, u = i.order.length; v < u; v++) {
			var x = i.order[v], w;
			if ((w = i.match[x].exec(z))) {
				var t = RegExp.leftContext;
				if (t.substr(t.length - 1) !== "\\") {
					w[1] = (w[1] || "").replace(/\\/g, "");
					y = i.find[x](w, e, A);
					if (y != null) {
						z = z.replace(i.match[x], "");
						break
					}
				}
			}
		}
		if (!y) {
			y = e.getElementsByTagName("*")
		}
		return {
			set :y,
			expr :z
		}
	};
	b.filter = function(C, B, F, v) {
		var u = C, H = [], z = B, x, e, y = B && B[0] && c(B[0]);
		while (C && B.length) {
			for ( var A in i.filter) {
				if ((x = i.match[A].exec(C)) != null) {
					var t = i.filter[A], G, E;
					e = false;
					if (z == H) {
						H = []
					}
					if (i.preFilter[A]) {
						x = i.preFilter[A](x, z, F, H, v, y);
						if (!x) {
							e = G = true
						} else {
							if (x === true) {
								continue
							}
						}
					}
					if (x) {
						for ( var w = 0; (E = z[w]) != null; w++) {
							if (E) {
								G = t(E, x, w, z);
								var D = v ^ !!G;
								if (F && G != null) {
									if (D) {
										e = true
									} else {
										z[w] = false
									}
								} else {
									if (D) {
										H.push(E);
										e = true
									}
								}
							}
						}
					}
					if (G !== undefined) {
						if (!F) {
							z = H
						}
						C = C.replace(i.match[A], "");
						if (!e) {
							return []
						}
						break
					}
				}
			}
			if (C == u) {
				if (e == null) {
					throw "Syntax error, unrecognized expression: " + C
				} else {
					break
				}
			}
			u = C
		}
		return z
	};
	var i = b.selectors = {
		order : [ "ID", "NAME", "TAG" ],
		match : {
			ID :/#((?:[\w\u00c0-\uFFFF_-]|\\.)+)/,
			CLASS :/\.((?:[\w\u00c0-\uFFFF_-]|\\.)+)/,
			NAME :/\[name=['"]*((?:[\w\u00c0-\uFFFF_-]|\\.)+)['"]*\]/,
			ATTR :/\[\s*((?:[\w\u00c0-\uFFFF_-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/,
			TAG :/^((?:[\w\u00c0-\uFFFF\*_-]|\\.)+)/,
			CHILD :/:(only|nth|last|first)-child(?:\((even|odd|[\dn+-]*)\))?/,
			POS :/:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^-]|$)/,
			PSEUDO :/:((?:[\w\u00c0-\uFFFF_-]|\\.)+)(?:\((['"]*)((?:\([^\)]+\)|[^\2\(\)]*)+)\2\))?/
		},
		attrMap : {
			"class" :"className",
			"for" :"htmlFor"
		},
		attrHandle : {
			href : function(e) {
				return e.getAttribute("href")
			}
		},
		relative : {
			"+" : function(z, e, y) {
				var w = typeof e === "string", A = w && !/\W/.test(e), x = w
						&& !A;
				if (A && !y) {
					e = e.toUpperCase()
				}
				for ( var v = 0, u = z.length, t; v < u; v++) {
					if ((t = z[v])) {
						while ((t = t.previousSibling) && t.nodeType !== 1) {
						}
						z[v] = x || t && t.nodeName === e ? t || false
								: t === e
					}
				}
				if (x) {
					b.filter(e, z, true)
				}
			},
			">" : function(y, t, z) {
				var w = typeof t === "string";
				if (w && !/\W/.test(t)) {
					t = z ? t : t.toUpperCase();
					for ( var u = 0, e = y.length; u < e; u++) {
						var x = y[u];
						if (x) {
							var v = x.parentNode;
							y[u] = v.nodeName === t ? v : false
						}
					}
				} else {
					for ( var u = 0, e = y.length; u < e; u++) {
						var x = y[u];
						if (x) {
							y[u] = w ? x.parentNode : x.parentNode === t
						}
					}
					if (w) {
						b.filter(t, y, true)
					}
				}
			},
			"" : function(v, t, x) {
				var u = m++, e = q;
				if (!t.match(/\W/)) {
					var w = t = x ? t : t.toUpperCase();
					e = a
				}
				e("parentNode", t, u, v, w, x)
			},
			"~" : function(v, t, x) {
				var u = m++, e = q;
				if (typeof t === "string" && !t.match(/\W/)) {
					var w = t = x ? t : t.toUpperCase();
					e = a
				}
				e("previousSibling", t, u, v, w, x)
			}
		},
		find : {
			ID : function(t, u, v) {
				if (typeof u.getElementById !== "undefined" && !v) {
					var e = u.getElementById(t[1]);
					return e ? [ e ] : []
				}
			},
			NAME : function(u, x, y) {
				if (typeof x.getElementsByName !== "undefined") {
					var t = [], w = x.getElementsByName(u[1]);
					for ( var v = 0, e = w.length; v < e; v++) {
						if (w[v].getAttribute("name") === u[1]) {
							t.push(w[v])
						}
					}
					return t.length === 0 ? null : t
				}
			},
			TAG : function(e, t) {
				return t.getElementsByTagName(e[1])
			}
		},
		preFilter : {
			CLASS : function(v, t, u, e, y, z) {
				v = " " + v[1].replace(/\\/g, "") + " ";
				if (z) {
					return v
				}
				for ( var w = 0, x; (x = t[w]) != null; w++) {
					if (x) {
						if (y
								^ (x.className && (" " + x.className + " ")
										.indexOf(v) >= 0)) {
							if (!u) {
								e.push(x)
							}
						} else {
							if (u) {
								t[w] = false
							}
						}
					}
				}
				return false
			},
			ID : function(e) {
				return e[1].replace(/\\/g, "")
			},
			TAG : function(t, e) {
				for ( var u = 0; e[u] === false; u++) {
				}
				return e[u] && c(e[u]) ? t[1] : t[1].toUpperCase()
			},
			CHILD : function(e) {
				if (e[1] == "nth") {
					var t = /(-?)(\d*)n((?:\+|-)?\d*)/.exec(e[2] == "even"
							&& "2n" || e[2] == "odd" && "2n+1"
							|| !/\D/.test(e[2]) && "0n+" + e[2] || e[2]);
					e[2] = (t[1] + (t[2] || 1)) - 0;
					e[3] = t[3] - 0
				}
				e[0] = m++;
				return e
			},
			ATTR : function(w, t, u, e, x, y) {
				var v = w[1].replace(/\\/g, "");
				if (!y && i.attrMap[v]) {
					w[1] = i.attrMap[v]
				}
				if (w[2] === "~=") {
					w[4] = " " + w[4] + " "
				}
				return w
			},
			PSEUDO : function(w, t, u, e, x) {
				if (w[1] === "not") {
					if (w[3].match(l).length > 1 || /^\w/.test(w[3])) {
						w[3] = b(w[3], null, null, t)
					} else {
						var v = b.filter(w[3], t, u, true ^ x);
						if (!u) {
							e.push.apply(e, v)
						}
						return false
					}
				} else {
					if (i.match.POS.test(w[0]) || i.match.CHILD.test(w[0])) {
						return true
					}
				}
				return w
			},
			POS : function(e) {
				e.unshift(true);
				return e
			}
		},
		filters : {
			enabled : function(e) {
				return e.disabled === false && e.type !== "hidden"
			},
			disabled : function(e) {
				return e.disabled === true
			},
			checked : function(e) {
				return e.checked === true
			},
			selected : function(e) {
				e.parentNode.selectedIndex;
				return e.selected === true
			},
			parent : function(e) {
				return !!e.firstChild
			},
			empty : function(e) {
				return !e.firstChild
			},
			has : function(u, t, e) {
				return !!b(e[3], u).length
			},
			header : function(e) {
				return /h\d/i.test(e.nodeName)
			},
			text : function(e) {
				return "text" === e.type
			},
			radio : function(e) {
				return "radio" === e.type
			},
			checkbox : function(e) {
				return "checkbox" === e.type
			},
			file : function(e) {
				return "file" === e.type
			},
			password : function(e) {
				return "password" === e.type
			},
			submit : function(e) {
				return "submit" === e.type
			},
			image : function(e) {
				return "image" === e.type
			},
			reset : function(e) {
				return "reset" === e.type
			},
			button : function(e) {
				return "button" === e.type
						|| e.nodeName.toUpperCase() === "BUTTON"
			},
			input : function(e) {
				return /input|select|textarea|button/i.test(e.nodeName)
			}
		},
		setFilters : {
			first : function(t, e) {
				return e === 0
			},
			last : function(u, t, e, v) {
				return t === v.length - 1
			},
			even : function(t, e) {
				return e % 2 === 0
			},
			odd : function(t, e) {
				return e % 2 === 1
			},
			lt : function(u, t, e) {
				return t < e[3] - 0
			},
			gt : function(u, t, e) {
				return t > e[3] - 0
			},
			nth : function(u, t, e) {
				return e[3] - 0 == t
			},
			eq : function(u, t, e) {
				return e[3] - 0 == t
			}
		},
		filter : {
			PSEUDO : function(y, u, v, z) {
				var t = u[1], w = i.filters[t];
				if (w) {
					return w(y, v, u, z)
				} else {
					if (t === "contains") {
						return (y.textContent || y.innerText || "")
								.indexOf(u[3]) >= 0
					} else {
						if (t === "not") {
							var x = u[3];
							for ( var v = 0, e = x.length; v < e; v++) {
								if (x[v] === y) {
									return false
								}
							}
							return true
						}
					}
				}
			},
			CHILD : function(e, v) {
				var y = v[1], t = e;
				switch (y) {
				case "only":
				case "first":
					while (t = t.previousSibling) {
						if (t.nodeType === 1) {
							return false
						}
					}
					if (y == "first") {
						return true
					}
					t = e;
				case "last":
					while (t = t.nextSibling) {
						if (t.nodeType === 1) {
							return false
						}
					}
					return true;
				case "nth":
					var u = v[2], B = v[3];
					if (u == 1 && B == 0) {
						return true
					}
					var x = v[0], A = e.parentNode;
					if (A && (A.sizcache !== x || !e.nodeIndex)) {
						var w = 0;
						for (t = A.firstChild; t; t = t.nextSibling) {
							if (t.nodeType === 1) {
								t.nodeIndex = ++w
							}
						}
						A.sizcache = x
					}
					var z = e.nodeIndex - B;
					if (u == 0) {
						return z == 0
					} else {
						return (z % u == 0 && z / u >= 0)
					}
				}
			},
			ID : function(t, e) {
				return t.nodeType === 1 && t.getAttribute("id") === e
			},
			TAG : function(t, e) {
				return (e === "*" && t.nodeType === 1) || t.nodeName === e
			},
			CLASS : function(t, e) {
				return (" " + (t.className || t.getAttribute("class")) + " ")
						.indexOf(e) > -1
			},
			ATTR : function(x, v) {
				var u = v[1], e = i.attrHandle[u] ? i.attrHandle[u](x)
						: x[u] != null ? x[u] : x.getAttribute(u), y = e + "", w = v[2], t = v[4];
				return e == null ? w === "!="
						: w === "=" ? y === t
								: w === "*=" ? y.indexOf(t) >= 0
										: w === "~=" ? (" " + y + " ")
												.indexOf(t) >= 0
												: !t ? y && e !== false
														: w === "!=" ? y != t
																: w === "^=" ? y
																		.indexOf(t) === 0
																		: w === "$=" ? y
																				.substr(y.length
																						- t.length) === t
																				: w === "|=" ? y === t
																						|| y
																								.substr(
																										0,
																										t.length + 1) === t
																								+ "-"
																						: false
			},
			POS : function(w, t, u, x) {
				var e = t[2], v = i.setFilters[e];
				if (v) {
					return v(w, u, t, x)
				}
			}
		}
	};
	var h = i.match.POS;
	for ( var d in i.match) {
		i.match[d] = new RegExp(i.match[d].source
				+ /(?![^\[]*\])(?![^\(]*\))/.source)
	}
	var k = function(t, e) {
		t = Array.prototype.slice.call(t);
		if (e) {
			r.apply(e, t);
			return e
		}
		return t
	};
	try {
		Array.prototype.slice.call(document.documentElement.childNodes)
	} catch (s) {
		k = function(w, v) {
			var t = v || [];
			if (o.call(w) === "[object Array]") {
				Array.prototype.push.apply(t, w)
			} else {
				if (typeof w.length === "number") {
					for ( var u = 0, e = w.length; u < e; u++) {
						t.push(w[u])
					}
				} else {
					for ( var u = 0; w[u]; u++) {
						t.push(w[u])
					}
				}
			}
			return t
		}
	}
	var n;
	if (document.documentElement.compareDocumentPosition) {
		n = function(t, e) {
			var u = t.compareDocumentPosition(e) & 4 ? -1 : t === e ? 0 : 1;
			if (u === 0) {
				hasDuplicate = true
			}
			return u
		}
	} else {
		if ("sourceIndex" in document.documentElement) {
			n = function(t, e) {
				var u = t.sourceIndex - e.sourceIndex;
				if (u === 0) {
					hasDuplicate = true
				}
				return u
			}
		} else {
			if (document.createRange) {
				n = function(v, t) {
					var u = v.ownerDocument.createRange(), e = t.ownerDocument
							.createRange();
					u.selectNode(v);
					u.collapse(true);
					e.selectNode(t);
					e.collapse(true);
					var w = u.compareBoundaryPoints(Range.START_TO_END, e);
					if (w === 0) {
						hasDuplicate = true
					}
					return w
				}
			}
		}
	}
	( function() {
		var t = document.createElement("form"), u = "script"
				+ (new Date).getTime();
		t.innerHTML = "<input name='" + u + "'/>";
		var e = document.documentElement;
		e.insertBefore(t, e.firstChild);
		if (!!document.getElementById(u)) {
			i.find.ID = function(w, x, y) {
				if (typeof x.getElementById !== "undefined" && !y) {
					var v = x.getElementById(w[1]);
					return v ? v.id === w[1]
							|| typeof v.getAttributeNode !== "undefined"
							&& v.getAttributeNode("id").nodeValue === w[1] ? [ v ]
							: undefined
							: []
				}
			};
			i.filter.ID = function(x, v) {
				var w = typeof x.getAttributeNode !== "undefined"
						&& x.getAttributeNode("id");
				return x.nodeType === 1 && w && w.nodeValue === v
			}
		}
		e.removeChild(t)
	})();
	( function() {
		var e = document.createElement("div");
		e.appendChild(document.createComment(""));
		if (e.getElementsByTagName("*").length > 0) {
			i.find.TAG = function(t, x) {
				var w = x.getElementsByTagName(t[1]);
				if (t[1] === "*") {
					var v = [];
					for ( var u = 0; w[u]; u++) {
						if (w[u].nodeType === 1) {
							v.push(w[u])
						}
					}
					w = v
				}
				return w
			}
		}
		e.innerHTML = "<a href='#'></a>";
		if (e.firstChild && typeof e.firstChild.getAttribute !== "undefined"
				&& e.firstChild.getAttribute("href") !== "#") {
			i.attrHandle.href = function(t) {
				return t.getAttribute("href", 2)
			}
		}
	})();
	if (document.querySelectorAll) {
		( function() {
			var e = b, u = document.createElement("div");
			u.innerHTML = "<p class='TEST'></p>";
			if (u.querySelectorAll && u.querySelectorAll(".TEST").length === 0) {
				return
			}
			b = function(y, x, v, w) {
				x = x || document;
				if (!w && x.nodeType === 9 && !c(x)) {
					try {
						return k(x.querySelectorAll(y), v)
					} catch (z) {
					}
				}
				return e(y, x, v, w)
			};
			for ( var t in e) {
				b[t] = e[t]
			}
		})()
	}
	if (document.getElementsByClassName
			&& document.documentElement.getElementsByClassName) {
		( function() {
			var e = document.createElement("div");
			e.innerHTML = "<div class='test e'></div><div class='test'></div>";
			if (e.getElementsByClassName("e").length === 0) {
				return
			}
			e.lastChild.className = "e";
			if (e.getElementsByClassName("e").length === 1) {
				return
			}
			i.order.splice(1, 0, "CLASS");
			i.find.CLASS = function(t, u, v) {
				if (typeof u.getElementsByClassName !== "undefined" && !v) {
					return u.getElementsByClassName(t[1])
				}
			}
		})()
	}
	function a(t, y, x, C, z, B) {
		var A = t == "previousSibling" && !B;
		for ( var v = 0, u = C.length; v < u; v++) {
			var e = C[v];
			if (e) {
				if (A && e.nodeType === 1) {
					e.sizcache = x;
					e.sizset = v
				}
				e = e[t];
				var w = false;
				while (e) {
					if (e.sizcache === x) {
						w = C[e.sizset];
						break
					}
					if (e.nodeType === 1 && !B) {
						e.sizcache = x;
						e.sizset = v
					}
					if (e.nodeName === y) {
						w = e;
						break
					}
					e = e[t]
				}
				C[v] = w
			}
		}
	}
	function q(t, y, x, C, z, B) {
		var A = t == "previousSibling" && !B;
		for ( var v = 0, u = C.length; v < u; v++) {
			var e = C[v];
			if (e) {
				if (A && e.nodeType === 1) {
					e.sizcache = x;
					e.sizset = v
				}
				e = e[t];
				var w = false;
				while (e) {
					if (e.sizcache === x) {
						w = C[e.sizset];
						break
					}
					if (e.nodeType === 1) {
						if (!B) {
							e.sizcache = x;
							e.sizset = v
						}
						if (typeof y !== "string") {
							if (e === y) {
								w = true;
								break
							}
						} else {
							if (b.filter(y, [ e ]).length > 0) {
								w = e;
								break
							}
						}
					}
					e = e[t]
				}
				C[v] = w
			}
		}
	}
	var j = document.compareDocumentPosition ? function(t, e) {
		return t.compareDocumentPosition(e) & 16
	} : function(t, e) {
		return t !== e && (t.contains ? t.contains(e) : true)
	};
	var c = function(e) {
		return e.nodeType === 9 && e.documentElement.nodeName !== "HTML"
				|| !!e.ownerDocument
				&& e.ownerDocument.documentElement.nodeName !== "HTML"
	};
	var p = function(e, z) {
		var v = [], w = "", x, u = z.nodeType ? [ z ] : z;
		while ((x = i.match.PSEUDO.exec(e))) {
			w += x[0];
			e = e.replace(i.match.PSEUDO, "")
		}
		e = i.relative[e] ? e + "*" : e;
		for ( var y = 0, t = u.length; y < t; y++) {
			b(e, u[y], v)
		}
		return b.filter(w, v)
	};
	window.tinymce.dom.Sizzle = b
})();
( function(d) {
	var f = d.each, c = d.DOM, b = d.isIE, e = d.isWebKit, a;
	d
			.create(
					"static tinymce.dom.Event",
					{
						inits : [],
						events : [],
						add : function(m, p, l, j) {
							var g, h = this, i = h.events, k;
							if (m && m.hasOwnProperty && m instanceof Array) {
								k = [];
								f(m, function(n) {
									n = c.get(n);
									k.push(h.add(n, p, l, j))
								});
								return k
							}
							m = c.get(m);
							if (!m) {
								return
							}
							g = function(n) {
								n = n || window.event;
								if (n && !n.target && b) {
									n.target = n.srcElement
								}
								if (!j) {
									return l(n)
								}
								return l.call(j, n)
							};
							if (p == "unload") {
								d.unloads.unshift( {
									func :g
								});
								return g
							}
							if (p == "init") {
								if (h.domLoaded) {
									g()
								} else {
									h.inits.push(g)
								}
								return g
							}
							i.push( {
								obj :m,
								name :p,
								func :l,
								cfunc :g,
								scope :j
							});
							h._add(m, p, g);
							return l
						},
						remove : function(l, m, k) {
							var h = this, g = h.events, i = false, j;
							if (l && l.hasOwnProperty && l instanceof Array) {
								j = [];
								f(l, function(n) {
									n = c.get(n);
									j.push(h.remove(n, m, k))
								});
								return j
							}
							l = c.get(l);
							f(
									g,
									function(o, n) {
										if (o.obj == l
												&& o.name == m
												&& (!k || (o.func == k || o.cfunc == k))) {
											g.splice(n, 1);
											h._remove(l, m, o.cfunc);
											i = true;
											return false
										}
									});
							return i
						},
						clear : function(l) {
							var j = this, g = j.events, h, k;
							if (l) {
								l = c.get(l);
								for (h = g.length - 1; h >= 0; h--) {
									k = g[h];
									if (k.obj === l) {
										j._remove(k.obj, k.name, k.cfunc);
										k.obj = k.cfunc = null;
										g.splice(h, 1)
									}
								}
							}
						},
						cancel : function(g) {
							if (!g) {
								return false
							}
							this.stop(g);
							return this.prevent(g)
						},
						stop : function(g) {
							if (g.stopPropagation) {
								g.stopPropagation()
							} else {
								g.cancelBubble = true
							}
							return false
						},
						prevent : function(g) {
							if (g.preventDefault) {
								g.preventDefault()
							} else {
								g.returnValue = false
							}
							return false
						},
						_unload : function() {
							var g = a;
							f(g.events, function(j, h) {
								g._remove(j.obj, j.name, j.cfunc);
								j.obj = j.cfunc = null
							});
							g.events = [];
							g = null
						},
						_add : function(h, i, g) {
							if (h.attachEvent) {
								h.attachEvent("on" + i, g)
							} else {
								if (h.addEventListener) {
									h.addEventListener(i, g, false)
								} else {
									h["on" + i] = g
								}
							}
						},
						_remove : function(i, j, h) {
							if (i) {
								try {
									if (i.detachEvent) {
										i.detachEvent("on" + j, h)
									} else {
										if (i.removeEventListener) {
											i.removeEventListener(j, h, false)
										} else {
											i["on" + j] = null
										}
									}
								} catch (g) {
								}
							}
						},
						_pageInit : function() {
							var g = a;
							if (g.domLoaded) {
								return
							}
							g._remove(window, "DOMContentLoaded", g._pageInit);
							g.domLoaded = true;
							f(g.inits, function(h) {
								h()
							});
							g.inits = []
						},
						_wait : function() {
							var g;
							if (window.tinyMCE_GZ && tinyMCE_GZ.loaded) {
								a.domLoaded = 1;
								return
							}
							if (b && document.location.protocol != "https:") {
								document
										.write("<script id=__ie_onload defer src='javascript:\"\"';><\/script>");
								c.get("__ie_onload").onreadystatechange = function() {
									if (this.readyState == "complete") {
										a._pageInit();
										c.get("__ie_onload").onreadystatechange = null
									}
								}
							} else {
								a._add(window, "DOMContentLoaded", a._pageInit,
										a);
								if (b || e) {
									g = setInterval( function() {
										if (/loaded|complete/
												.test(document.readyState)) {
											clearInterval(g);
											a._pageInit()
										}
									}, 10)
								}
							}
						}
					});
	a = d.dom.Event;
	a._wait();
	d.addUnload(a._unload)
})(tinymce);
( function(a) {
	var b = a.each;
	a.create("tinymce.dom.Element", {
		Element : function(g, e) {
			var c = this, f, d;
			e = e || {};
			c.id = g;
			c.dom = f = e.dom || a.DOM;
			c.settings = e;
			if (!a.isIE) {
				d = c.dom.get(c.id)
			}
			b( [ "getPos", "getRect", "getParent", "add", "setStyle",
					"getStyle", "setStyles", "setAttrib", "setAttribs",
					"getAttrib", "addClass", "removeClass", "hasClass",
					"getOuterHTML", "setOuterHTML", "remove", "show", "hide",
					"isHidden", "setHTML", "get" ], function(h) {
				c[h] = function() {
					var j = [ g ], k;
					for (k = 0; k < arguments.length; k++) {
						j.push(arguments[k])
					}
					j = f[h].apply(f, j);
					c.update(h);
					return j
				}
			})
		},
		on : function(e, d, c) {
			return a.dom.Event.add(this.id, e, d, c)
		},
		getXY : function() {
			return {
				x :parseInt(this.getStyle("left")),
				y :parseInt(this.getStyle("top"))
			}
		},
		getSize : function() {
			var c = this.dom.get(this.id);
			return {
				w :parseInt(this.getStyle("width") || c.clientWidth),
				h :parseInt(this.getStyle("height") || c.clientHeight)
			}
		},
		moveTo : function(c, d) {
			this.setStyles( {
				left :c,
				top :d
			})
		},
		moveBy : function(c, e) {
			var d = this.getXY();
			this.moveTo(d.x + c, d.y + e)
		},
		resizeTo : function(c, d) {
			this.setStyles( {
				width :c,
				height :d
			})
		},
		resizeBy : function(c, e) {
			var d = this.getSize();
			this.resizeTo(d.w + c, d.h + e)
		},
		update : function(d) {
			var e = this, c, f = e.dom;
			if (a.isIE6 && e.settings.blocker) {
				d = d || "";
				if (d.indexOf("get") === 0 || d.indexOf("has") === 0
						|| d.indexOf("is") === 0) {
					return
				}
				if (d == "remove") {
					f.remove(e.blocker);
					return
				}
				if (!e.blocker) {
					e.blocker = f.uniqueId();
					c = f.add(e.settings.container || f.getRoot(), "iframe", {
						id :e.blocker,
						style :"position:absolute;",
						frameBorder :0,
						src :'javascript:""'
					});
					f.setStyle(c, "opacity", 0)
				} else {
					c = f.get(e.blocker)
				}
				f.setStyle(c, "left", e.getStyle("left", 1));
				f.setStyle(c, "top", e.getStyle("top", 1));
				f.setStyle(c, "width", e.getStyle("width", 1));
				f.setStyle(c, "height", e.getStyle("height", 1));
				f.setStyle(c, "display", e.getStyle("display", 1));
				f.setStyle(c, "zIndex",
						parseInt(e.getStyle("zIndex", 1) || 0) - 1)
			}
		}
	})
})(tinymce);
( function(c) {
	function e(f) {
		return f.replace(/[\n\r]+/g, "")
	}
	var b = c.is, a = c.isIE, d = c.each;
	c
			.create(
					"tinymce.dom.Selection",
					{
						Selection : function(i, h, g) {
							var f = this;
							f.dom = i;
							f.win = h;
							f.serializer = g;
							d( [ "onBeforeSetContent", "onBeforeGetContent",
									"onSetContent", "onGetContent" ], function(
									j) {
								f[j] = new c.util.Dispatcher(f)
							});
							if (!f.win.getSelection) {
								f.tridentSel = new c.dom.TridentSelection(f)
							}
							c.addUnload(f.destroy, f)
						},
						getContent : function(g) {
							var f = this, h = f.getRng(), l = f.dom
									.create("body"), j = f.getSel(), i, k, m;
							g = g || {};
							i = k = "";
							g.get = true;
							g.format = g.format || "html";
							f.onBeforeGetContent.dispatch(f, g);
							if (g.format == "text") {
								return f.isCollapsed() ? ""
										: (h.text || (j.toString ? j.toString()
												: ""))
							}
							if (h.cloneContents) {
								m = h.cloneContents();
								if (m) {
									l.appendChild(m)
								}
							} else {
								if (b(h.item) || b(h.htmlText)) {
									l.innerHTML = h.item ? h.item(0).outerHTML
											: h.htmlText
								} else {
									l.innerHTML = h.toString()
								}
							}
							if (/^\s/.test(l.innerHTML)) {
								i = " "
							}
							if (/\s+$/.test(l.innerHTML)) {
								k = " "
							}
							g.getInner = true;
							g.content = f.isCollapsed() ? "" : i
									+ f.serializer.serialize(l, g) + k;
							f.onGetContent.dispatch(f, g);
							return g.content
						},
						setContent : function(i, g) {
							var f = this, j = f.getRng(), l, k = f.win.document;
							g = g || {
								format :"html"
							};
							g.set = true;
							i = g.content = f.dom.processHTML(i);
							f.onBeforeSetContent.dispatch(f, g);
							i = g.content;
							if (j.insertNode) {
								i += '<span id="__caret">_</span>';
								j.deleteContents();
								j.insertNode(f.getRng()
										.createContextualFragment(i));
								l = f.dom.get("__caret");
								j = k.createRange();
								j.setStartBefore(l);
								j.setEndAfter(l);
								f.setRng(j);
								f.dom.remove("__caret")
							} else {
								if (j.item) {
									k.execCommand("Delete", false, null);
									j = f.getRng()
								}
								j.pasteHTML(i)
							}
							f.onSetContent.dispatch(f, g)
						},
						getStart : function() {
							var f = this, g = f.getRng(), h;
							if (a) {
								if (g.item) {
									return g.item(0)
								}
								g = g.duplicate();
								g.collapse(1);
								h = g.parentElement();
								if (h && h.nodeName == "BODY") {
									return h.firstChild
								}
								return h
							} else {
								h = g.startContainer;
								if (h.nodeName == "BODY") {
									return h.firstChild
								}
								return f.dom.getParent(h, "*")
							}
						},
						getEnd : function() {
							var f = this, g = f.getRng(), h;
							if (a) {
								if (g.item) {
									return g.item(0)
								}
								g = g.duplicate();
								g.collapse(0);
								h = g.parentElement();
								if (h && h.nodeName == "BODY") {
									return h.lastChild
								}
								return h
							} else {
								h = g.endContainer;
								if (h.nodeName == "BODY") {
									return h.lastChild
								}
								return f.dom.getParent(h, "*")
							}
						},
						getBookmark : function(x) {
							var j = this, m = j.getRng(), f, n, l, u = j.dom
									.getViewPort(j.win), v, p, z, o, w = -16777215, k, h = j.dom
									.getRoot(), g = 0, i = 0, y;
							n = u.x;
							l = u.y;
							if (x == "simple") {
								return {
									rng :m,
									scrollX :n,
									scrollY :l
								}
							}
							if (a) {
								if (m.item) {
									v = m.item(0);
									d(j.dom.select(v.nodeName), function(s, r) {
										if (v == s) {
											p = r;
											return false
										}
									});
									return {
										tag :v.nodeName,
										index :p,
										scrollX :n,
										scrollY :l
									}
								}
								f = j.dom.doc.body.createTextRange();
								f.moveToElementText(h);
								f.collapse(true);
								z = Math.abs(f.move("character", w));
								f = m.duplicate();
								f.collapse(true);
								p = Math.abs(f.move("character", w));
								f = m.duplicate();
								f.collapse(false);
								o = Math.abs(f.move("character", w)) - p;
								return {
									start :p - z,
									length :o,
									scrollX :n,
									scrollY :l
								}
							}
							v = j.getNode();
							k = j.getSel();
							if (!k) {
								return null
							}
							if (v && v.nodeName == "IMG") {
								return {
									scrollX :n,
									scrollY :l
								}
							}
							function q(A, D, t) {
								var s = j.dom.doc.createTreeWalker(A,
										NodeFilter.SHOW_TEXT, null, false), E, B = 0, C = {};
								while ((E = s.nextNode()) != null) {
									if (E == D) {
										C.start = B
									}
									if (E == t) {
										C.end = B;
										return C
									}
									B += e(E.nodeValue || "").length
								}
								return null
							}
							if (k.anchorNode == k.focusNode
									&& k.anchorOffset == k.focusOffset) {
								v = q(h, k.anchorNode, k.focusNode);
								if (!v) {
									return {
										scrollX :n,
										scrollY :l
									}
								}
								e(k.anchorNode.nodeValue || "").replace(/^\s+/,
										function(r) {
											g = r.length
										});
								return {
									start :Math.max(v.start + k.anchorOffset
											- g, 0),
									end :Math.max(v.end + k.focusOffset - g, 0),
									scrollX :n,
									scrollY :l,
									beg :k.anchorOffset - g == 0
								}
							} else {
								v = q(h, m.startContainer, m.endContainer);
								if (!v) {
									return {
										scrollX :n,
										scrollY :l
									}
								}
								return {
									start :Math.max(
											v.start + m.startOffset - g, 0),
									end :Math.max(v.end + m.endOffset - i, 0),
									scrollX :n,
									scrollY :l,
									beg :m.startOffset - g == 0
								}
							}
						},
						moveToBookmark : function(n) {
							var o = this, g = o.getRng(), p = o.getSel(), j = o.dom
									.getRoot(), m, h, k;
							function i(q, t, D) {
								var B = o.dom.doc.createTreeWalker(q,
										NodeFilter.SHOW_TEXT, null, false), x, s = 0, A = {}, u, C, z, y;
								while ((x = B.nextNode()) != null) {
									z = y = 0;
									k = x.nodeValue || "";
									h = e(k).length;
									s += h;
									if (s >= t && !A.startNode) {
										u = t - (s - h);
										if (n.beg && u >= h) {
											continue
										}
										A.startNode = x;
										A.startOffset = u + y
									}
									if (s >= D) {
										A.endNode = x;
										A.endOffset = D - (s - h) + y;
										return A
									}
								}
								return null
							}
							if (!n) {
								return false
							}
							o.win.scrollTo(n.scrollX, n.scrollY);
							if (a) {
								if (g = n.rng) {
									try {
										g.select()
									} catch (l) {
									}
									return true
								}
								o.win.focus();
								if (n.tag) {
									g = j.createControlRange();
									d(o.dom.select(n.tag), function(r, q) {
										if (q == n.index) {
											g.addElement(r)
										}
									})
								} else {
									try {
										if (n.start < 0) {
											return true
										}
										g = p.createRange();
										g.moveToElementText(j);
										g.collapse(true);
										g.moveStart("character", n.start);
										g.moveEnd("character", n.length)
									} catch (f) {
										return true
									}
								}
								try {
									g.select()
								} catch (l) {
								}
								return true
							}
							if (!p) {
								return false
							}
							if (n.rng) {
								p.removeAllRanges();
								p.addRange(n.rng)
							} else {
								if (b(n.start) && b(n.end)) {
									try {
										m = i(j, n.start, n.end);
										if (m) {
											g = o.dom.doc.createRange();
											g.setStart(m.startNode,
													m.startOffset);
											g.setEnd(m.endNode, m.endOffset);
											p.removeAllRanges();
											p.addRange(g)
										}
										if (!c.isOpera) {
											o.win.focus()
										}
									} catch (l) {
									}
								}
							}
						},
						select : function(g, l) {
							var p = this, f = p.getRng(), q = p.getSel(), o, m, k, j = p.win.document;
							function h(u, t) {
								var s, r;
								if (u) {
									s = j.createTreeWalker(u,
											NodeFilter.SHOW_TEXT, null, false);
									while (u = s.nextNode()) {
										r = u;
										if (c.trim(u.nodeValue).length != 0) {
											if (t) {
												return u
											} else {
												r = u
											}
										}
									}
								}
								return r
							}
							if (a) {
								try {
									o = j.body;
									if (/^(IMG|TABLE)$/.test(g.nodeName)) {
										f = o.createControlRange();
										f.addElement(g)
									} else {
										f = o.createTextRange();
										f.moveToElementText(g)
									}
									f.select()
								} catch (i) {
								}
							} else {
								if (l) {
									m = h(g, 1)
											|| p.dom.select("br:first", g)[0];
									k = h(g, 0)
											|| p.dom.select("br:last", g)[0];
									if (m && k) {
										f = j.createRange();
										if (m.nodeName == "BR") {
											f.setStartBefore(m)
										} else {
											f.setStart(m, 0)
										}
										if (k.nodeName == "BR") {
											f.setEndBefore(k)
										} else {
											f.setEnd(k, k.nodeValue.length)
										}
									} else {
										f.selectNode(g)
									}
								} else {
									f.selectNode(g)
								}
								p.setRng(f)
							}
							return g
						},
						isCollapsed : function() {
							var f = this, h = f.getRng(), g = f.getSel();
							if (!h || h.item) {
								return false
							}
							return !g || h.boundingWidth == 0 || h.collapsed
						},
						collapse : function(f) {
							var g = this, h = g.getRng(), i;
							if (h.item) {
								i = h.item(0);
								h = this.win.document.body.createTextRange();
								h.moveToElementText(i)
							}
							h.collapse(!!f);
							g.setRng(h)
						},
						getSel : function() {
							var g = this, f = this.win;
							return f.getSelection ? f.getSelection()
									: f.document.selection
						},
						getRng : function(j) {
							var g = this, h, i;
							if (j && g.tridentSel) {
								return g.tridentSel.getRangeAt(0)
							}
							try {
								if (h = g.getSel()) {
									i = h.rangeCount > 0 ? h.getRangeAt(0)
											: (h.createRange ? h.createRange()
													: g.win.document
															.createRange())
								}
							} catch (f) {
							}
							if (!i) {
								i = a ? g.win.document.body.createTextRange()
										: g.win.document.createRange()
							}
							return i
						},
						setRng : function(i) {
							var h, g = this;
							if (!g.tridentSel) {
								h = g.getSel();
								if (h) {
									h.removeAllRanges();
									h.addRange(i)
								}
							} else {
								if (i.cloneRange) {
									g.tridentSel.addRange(i);
									return
								}
								try {
									i.select()
								} catch (f) {
								}
							}
						},
						setNode : function(g) {
							var f = this;
							f.setContent(f.dom.getOuterHTML(g));
							return g
						},
						getNode : function() {
							var f = this, h = f.getRng(), g = f.getSel(), i;
							if (!a) {
								if (!h) {
									return f.dom.getRoot()
								}
								i = h.commonAncestorContainer;
								if (!h.collapsed) {
									if (c.isWebKit && g.anchorNode
											&& g.anchorNode.nodeType == 1) {
										return g.anchorNode.childNodes[g.anchorOffset]
									}
									if (h.startContainer == h.endContainer) {
										if (h.startOffset - h.endOffset < 2) {
											if (h.startContainer
													.hasChildNodes()) {
												i = h.startContainer.childNodes[h.startOffset]
											}
										}
									}
								}
								return f.dom.getParent(i, "*")
							}
							return h.item ? h.item(0) : h.parentElement()
						},
						getSelectedBlocks : function(g, f) {
							var i = this, j = i.dom, m, h, l, k = [];
							m = j.getParent(g || i.getStart(), j.isBlock);
							h = j.getParent(f || i.getEnd(), j.isBlock);
							if (m) {
								k.push(m)
							}
							if (m && h && m != h) {
								l = m;
								while ((l = l.nextSibling) && l != h) {
									if (j.isBlock(l)) {
										k.push(l)
									}
								}
							}
							if (h && m != h) {
								k.push(h)
							}
							return k
						},
						destroy : function(g) {
							var f = this;
							f.win = null;
							if (!g) {
								c.removeUnload(f.destroy)
							}
						}
					})
})(tinymce);
( function(a) {
	a
			.create(
					"tinymce.dom.XMLWriter",
					{
						node :null,
						XMLWriter : function(c) {
							function b() {
								var e = document.implementation;
								if (!e || !e.createDocument) {
									try {
										return new ActiveXObject(
												"MSXML2.DOMDocument")
									} catch (d) {
									}
									try {
										return new ActiveXObject(
												"Microsoft.XmlDom")
									} catch (d) {
									}
								} else {
									return e.createDocument("", "", null)
								}
							}
							this.doc = b();
							this.valid = a.isOpera || a.isWebKit;
							this.reset()
						},
						reset : function() {
							var b = this, c = b.doc;
							if (c.firstChild) {
								c.removeChild(c.firstChild)
							}
							b.node = c.appendChild(c.createElement("html"))
						},
						writeStartElement : function(c) {
							var b = this;
							b.node = b.node.appendChild(b.doc.createElement(c))
						},
						writeAttribute : function(c, b) {
							if (this.valid) {
								b = b.replace(/>/g, "%MCGT%")
							}
							this.node.setAttribute(c, b)
						},
						writeEndElement : function() {
							this.node = this.node.parentNode
						},
						writeFullEndElement : function() {
							var b = this, c = b.node;
							c.appendChild(b.doc.createTextNode(""));
							b.node = c.parentNode
						},
						writeText : function(b) {
							if (this.valid) {
								b = b.replace(/>/g, "%MCGT%")
							}
							this.node.appendChild(this.doc.createTextNode(b))
						},
						writeCDATA : function(b) {
							this.node.appendChild(this.doc.createCDATA(b))
						},
						writeComment : function(b) {
							if (a.isIE) {
								b = b.replace(/^\-|\-$/g, " ")
							}
							this.node.appendChild(this.doc.createComment(b
									.replace(/\-\-/g, " ")))
						},
						getContent : function() {
							var b;
							b = this.doc.xml
									|| new XMLSerializer()
											.serializeToString(this.doc);
							b = b
									.replace(
											/<\?[^?]+\?>|<html>|<\/html>|<html\/>|<!DOCTYPE[^>]+>/g,
											"");
							b = b.replace(/ ?\/>/g, " />");
							if (this.valid) {
								b = b.replace(/\%MCGT%/g, "&gt;")
							}
							return b
						}
					})
})(tinymce);
( function(a) {
	a.create("tinymce.dom.StringWriter", {
		str :null,
		tags :null,
		count :0,
		settings :null,
		indent :null,
		StringWriter : function(b) {
			this.settings = a.extend( {
				indent_char :" ",
				indentation :1
			}, b);
			this.reset()
		},
		reset : function() {
			this.indent = "";
			this.str = "";
			this.tags = [];
			this.count = 0
		},
		writeStartElement : function(b) {
			this._writeAttributesEnd();
			this.writeRaw("<" + b);
			this.tags.push(b);
			this.inAttr = true;
			this.count++;
			this.elementCount = this.count
		},
		writeAttribute : function(d, b) {
			var c = this;
			c.writeRaw(" " + c.encode(d) + '="' + c.encode(b) + '"')
		},
		writeEndElement : function() {
			var b;
			if (this.tags.length > 0) {
				b = this.tags.pop();
				if (this._writeAttributesEnd(1)) {
					this.writeRaw("</" + b + ">")
				}
				if (this.settings.indentation > 0) {
					this.writeRaw("\n")
				}
			}
		},
		writeFullEndElement : function() {
			if (this.tags.length > 0) {
				this._writeAttributesEnd();
				this.writeRaw("</" + this.tags.pop() + ">");
				if (this.settings.indentation > 0) {
					this.writeRaw("\n")
				}
			}
		},
		writeText : function(b) {
			this._writeAttributesEnd();
			this.writeRaw(this.encode(b));
			this.count++
		},
		writeCDATA : function(b) {
			this._writeAttributesEnd();
			this.writeRaw("<![CDATA[" + b + "]]>");
			this.count++
		},
		writeComment : function(b) {
			this._writeAttributesEnd();
			this.writeRaw("<!-- " + b + "-->");
			this.count++
		},
		writeRaw : function(b) {
			this.str += b
		},
		encode : function(b) {
			return b.replace(/[<>&"]/g, function(c) {
				switch (c) {
				case "<":
					return "&lt;";
				case ">":
					return "&gt;";
				case "&":
					return "&amp;";
				case '"':
					return "&quot;"
				}
				return c
			})
		},
		getContent : function() {
			return this.str
		},
		_writeAttributesEnd : function(b) {
			if (!this.inAttr) {
				return
			}
			this.inAttr = false;
			if (b && this.elementCount == this.count) {
				this.writeRaw(" />");
				return false
			}
			this.writeRaw(">");
			return true
		}
	})
})(tinymce);
( function(e) {
	var g = e.extend, f = e.each, b = e.util.Dispatcher, d = e.isIE, a = e.isGecko;
	function c(h) {
		return h.replace(/([?+*])/g, ".$1")
	}
	e
			.create(
					"tinymce.dom.Serializer",
					{
						Serializer : function(j) {
							var i = this;
							i.key = 0;
							i.onPreProcess = new b(i);
							i.onPostProcess = new b(i);
							try {
								i.writer = new e.dom.XMLWriter()
							} catch (h) {
								i.writer = new e.dom.StringWriter()
							}
							i.settings = j = g(
									{
										dom :e.DOM,
										valid_nodes :0,
										node_filter :0,
										attr_filter :0,
										invalid_attrs :/^(mce_|_moz_)/,
										closed :/(br|hr|input|meta|img|link|param)/,
										entity_encoding :"named",
										entities :"160,nbsp,161,iexcl,162,cent,163,pound,164,curren,165,yen,166,brvbar,167,sect,168,uml,169,copy,170,ordf,171,laquo,172,not,173,shy,174,reg,175,macr,176,deg,177,plusmn,178,sup2,179,sup3,180,acute,181,micro,182,para,183,middot,184,cedil,185,sup1,186,ordm,187,raquo,188,frac14,189,frac12,190,frac34,191,iquest,192,Agrave,193,Aacute,194,Acirc,195,Atilde,196,Auml,197,Aring,198,AElig,199,Ccedil,200,Egrave,201,Eacute,202,Ecirc,203,Euml,204,Igrave,205,Iacute,206,Icirc,207,Iuml,208,ETH,209,Ntilde,210,Ograve,211,Oacute,212,Ocirc,213,Otilde,214,Ouml,215,times,216,Oslash,217,Ugrave,218,Uacute,219,Ucirc,220,Uuml,221,Yacute,222,THORN,223,szlig,224,agrave,225,aacute,226,acirc,227,atilde,228,auml,229,aring,230,aelig,231,ccedil,232,egrave,233,eacute,234,ecirc,235,euml,236,igrave,237,iacute,238,icirc,239,iuml,240,eth,241,ntilde,242,ograve,243,oacute,244,ocirc,245,otilde,246,ouml,247,divide,248,oslash,249,ugrave,250,uacute,251,ucirc,252,uuml,253,yacute,254,thorn,255,yuml,402,fnof,913,Alpha,914,Beta,915,Gamma,916,Delta,917,Epsilon,918,Zeta,919,Eta,920,Theta,921,Iota,922,Kappa,923,Lambda,924,Mu,925,Nu,926,Xi,927,Omicron,928,Pi,929,Rho,931,Sigma,932,Tau,933,Upsilon,934,Phi,935,Chi,936,Psi,937,Omega,945,alpha,946,beta,947,gamma,948,delta,949,epsilon,950,zeta,951,eta,952,theta,953,iota,954,kappa,955,lambda,956,mu,957,nu,958,xi,959,omicron,960,pi,961,rho,962,sigmaf,963,sigma,964,tau,965,upsilon,966,phi,967,chi,968,psi,969,omega,977,thetasym,978,upsih,982,piv,8226,bull,8230,hellip,8242,prime,8243,Prime,8254,oline,8260,frasl,8472,weierp,8465,image,8476,real,8482,trade,8501,alefsym,8592,larr,8593,uarr,8594,rarr,8595,darr,8596,harr,8629,crarr,8656,lArr,8657,uArr,8658,rArr,8659,dArr,8660,hArr,8704,forall,8706,part,8707,exist,8709,empty,8711,nabla,8712,isin,8713,notin,8715,ni,8719,prod,8721,sum,8722,minus,8727,lowast,8730,radic,8733,prop,8734,infin,8736,ang,8743,and,8744,or,8745,cap,8746,cup,8747,int,8756,there4,8764,sim,8773,cong,8776,asymp,8800,ne,8801,equiv,8804,le,8805,ge,8834,sub,8835,sup,8836,nsub,8838,sube,8839,supe,8853,oplus,8855,otimes,8869,perp,8901,sdot,8968,lceil,8969,rceil,8970,lfloor,8971,rfloor,9001,lang,9002,rang,9674,loz,9824,spades,9827,clubs,9829,hearts,9830,diams,338,OElig,339,oelig,352,Scaron,353,scaron,376,Yuml,710,circ,732,tilde,8194,ensp,8195,emsp,8201,thinsp,8204,zwnj,8205,zwj,8206,lrm,8207,rlm,8211,ndash,8212,mdash,8216,lsquo,8217,rsquo,8218,sbquo,8220,ldquo,8221,rdquo,8222,bdquo,8224,dagger,8225,Dagger,8240,permil,8249,lsaquo,8250,rsaquo,8364,euro",
										bool_attrs :/(checked|disabled|readonly|selected|nowrap)/,
										valid_elements :"*[*]",
										extended_valid_elements :0,
										valid_child_elements :0,
										invalid_elements :0,
										fix_table_elements :1,
										fix_list_elements :true,
										fix_content_duplication :true,
										convert_fonts_to_spans :false,
										font_size_classes :0,
										font_size_style_values :0,
										apply_source_formatting :0,
										indent_mode :"simple",
										indent_char :"\t",
										indent_levels :1,
										remove_linebreaks :1,
										remove_redundant_brs :1,
										element_format :"xhtml"
									}, j);
							i.dom = j.dom;
							if (j.remove_redundant_brs) {
								i.onPostProcess.add( function(k, l) {
									l.content = l.content.replace(
											/<br \/>(\s*<\/li>)/g, "$1")
								})
							}
							if (j.element_format == "html") {
								i.onPostProcess.add( function(k, l) {
									l.content = l.content.replace(
											/<([^>]+) \/>/g, "<$1>")
								})
							}
							if (j.fix_list_elements) {
								i.onPreProcess
										.add( function(v, s) {
											var l, y, w = [ "ol", "ul" ], u, t, q, k = /^(OL|UL)$/, z;
											function m(r, x) {
												var o = x.split(","), p;
												while ((r = r.previousSibling) != null) {
													for (p = 0; p < o.length; p++) {
														if (r.nodeName == o[p]) {
															return r
														}
													}
												}
												return null
											}
											for (y = 0; y < w.length; y++) {
												l = i.dom.select(w[y], s.node);
												for (u = 0; u < l.length; u++) {
													t = l[u];
													q = t.parentNode;
													if (k.test(q.nodeName)) {
														z = m(t, "LI");
														if (!z) {
															z = i.dom
																	.create("li");
															z.innerHTML = "&nbsp;";
															z.appendChild(t);
															q
																	.insertBefore(
																			z,
																			q.firstChild)
														} else {
															z.appendChild(t)
														}
													}
												}
											}
										})
							}
							if (j.fix_table_elements) {
								i.onPreProcess.add( function(k, l) {
									f(i.dom.select("p table", l.node),
											function(m) {
												i.dom.split(i.dom.getParent(m,
														"p"), m)
											})
								})
							}
						},
						setEntities : function(p) {
							var n = this, j, m, h = {}, o = "", k;
							if (n.entityLookup) {
								return
							}
							j = p.split(",");
							for (m = 0; m < j.length; m += 2) {
								k = j[m];
								if (k == 34 || k == 38 || k == 60 || k == 62) {
									continue
								}
								h[String.fromCharCode(j[m])] = j[m + 1];
								k = parseInt(j[m]).toString(16);
								o += "\\u" + "0000".substring(k.length) + k
							}
							if (!o) {
								n.settings.entity_encoding = "raw";
								return
							}
							n.entitiesRE = new RegExp("[" + o + "]", "g");
							n.entityLookup = h
						},
						setValidChildRules : function(h) {
							this.childRules = null;
							this.addValidChildRules(h)
						},
						addValidChildRules : function(k) {
							var j = this, l, h, i;
							if (!k) {
								return
							}
							l = "A|BR|SPAN|BDO|MAP|OBJECT|IMG|TT|I|B|BIG|SMALL|EM|STRONG|DFN|CODE|Q|SAMP|KBD|VAR|CITE|ABBR|ACRONYM|SUB|SUP|#text|#comment";
							h = "A|BR|SPAN|BDO|OBJECT|APPLET|IMG|MAP|IFRAME|TT|I|B|U|S|STRIKE|BIG|SMALL|FONT|BASEFONT|EM|STRONG|DFN|CODE|Q|SAMP|KBD|VAR|CITE|ABBR|ACRONYM|SUB|SUP|INPUT|SELECT|TEXTAREA|LABEL|BUTTON|#text|#comment";
							i = "H[1-6]|P|DIV|ADDRESS|PRE|FORM|TABLE|LI|OL|UL|TD|CAPTION|BLOCKQUOTE|CENTER|DL|DT|DD|DIR|FIELDSET|FORM|NOSCRIPT|NOFRAMES|MENU|ISINDEX|SAMP";
							f(k.split(","), function(n) {
								var o = n.split(/\[|\]/), m;
								n = "";
								f(o[1].split("|"), function(p) {
									if (n) {
										n += "|"
									}
									switch (p) {
									case "%itrans":
										p = h;
										break;
									case "%itrans_na":
										p = h.substring(2);
										break;
									case "%istrict":
										p = l;
										break;
									case "%istrict_na":
										p = l.substring(2);
										break;
									case "%btrans":
										p = i;
										break;
									case "%bstrict":
										p = i;
										break
									}
									n += p
								});
								m = new RegExp("^(" + n.toLowerCase() + ")$",
										"i");
								f(o[0].split("/"), function(p) {
									j.childRules = j.childRules || {};
									j.childRules[p] = m
								})
							});
							k = "";
							f(j.childRules, function(n, m) {
								if (k) {
									k += "|"
								}
								k += m
							});
							j.parentElementsRE = new RegExp("^("
									+ k.toLowerCase() + ")$", "i")
						},
						setRules : function(i) {
							var h = this;
							h._setup();
							h.rules = {};
							h.wildRules = [];
							h.validElements = {};
							return h.addRules(i)
						},
						addRules : function(i) {
							var h = this, j;
							if (!i) {
								return
							}
							h._setup();
							f(
									i.split(","),
									function(m) {
										var q = m.split(/\[|\]/), l = q[0]
												.split("/"), r, k, o, n = [];
										if (j) {
											k = e.extend( [], j.attribs)
										}
										if (q.length > 1) {
											f(
													q[1].split("|"),
													function(u) {
														var p = {}, t;
														k = k || [];
														u = u.replace(/::/g,
																"~");
														u = /^([!\-])?([\w*.?~_\-]+|)([=:<])?(.+)?$/
																.exec(u);
														u[2] = u[2].replace(
																/~/g, ":");
														if (u[1] == "!") {
															r = r || [];
															r.push(u[2])
														}
														if (u[1] == "-") {
															for (t = 0; t < k.length; t++) {
																if (k[t].name == u[2]) {
																	k.splice(t,
																			1);
																	return
																}
															}
														}
														switch (u[3]) {
														case "=":
															p.defaultVal = u[4]
																	|| "";
															break;
														case ":":
															p.forcedVal = u[4];
															break;
														case "<":
															p.validVals = u[4]
																	.split("?");
															break
														}
														if (/[*.?]/.test(u[2])) {
															o = o || [];
															p.nameRE = new RegExp(
																	"^"
																			+ c(u[2])
																			+ "$");
															o.push(p)
														} else {
															p.name = u[2];
															k.push(p)
														}
														n.push(u[2])
													})
										}
										f(
												l,
												function(v, u) {
													var w = v.charAt(0), t = 1, p = {};
													if (j) {
														if (j.noEmpty) {
															p.noEmpty = j.noEmpty
														}
														if (j.fullEnd) {
															p.fullEnd = j.fullEnd
														}
														if (j.padd) {
															p.padd = j.padd
														}
													}
													switch (w) {
													case "-":
														p.noEmpty = true;
														break;
													case "+":
														p.fullEnd = true;
														break;
													case "#":
														p.padd = true;
														break;
													default:
														t = 0
													}
													l[u] = v = v.substring(t);
													h.validElements[v] = 1;
													if (/[*.?]/.test(l[0])) {
														p.nameRE = new RegExp(
																"^" + c(l[0])
																		+ "$");
														h.wildRules = h.wildRules
																|| {};
														h.wildRules.push(p)
													} else {
														p.name = l[0];
														if (l[0] == "@") {
															j = p
														}
														h.rules[v] = p
													}
													p.attribs = k;
													if (r) {
														p.requiredAttribs = r
													}
													if (o) {
														v = "";
														f(n, function(s) {
															if (v) {
																v += "|"
															}
															v += "(" + c(s)
																	+ ")"
														});
														p.validAttribsRE = new RegExp(
																"^"
																		+ v
																				.toLowerCase()
																		+ "$");
														p.wildAttribs = o
													}
												})
									});
							i = "";
							f(h.validElements, function(m, l) {
								if (i) {
									i += "|"
								}
								if (l != "@") {
									i += l
								}
							});
							h.validElementsRE = new RegExp("^("
									+ c(i.toLowerCase()) + ")$")
						},
						findRule : function(m) {
							var j = this, l = j.rules, h, k;
							j._setup();
							k = l[m];
							if (k) {
								return k
							}
							l = j.wildRules;
							for (h = 0; h < l.length; h++) {
								if (l[h].nameRE.test(m)) {
									return l[h]
								}
							}
							return null
						},
						findAttribRule : function(h, l) {
							var j, k = h.wildAttribs;
							for (j = 0; j < k.length; j++) {
								if (k[j].nameRE.test(l)) {
									return k[j]
								}
							}
							return null
						},
						serialize : function(l, k) {
							var j, i = this;
							i._setup();
							k = k || {};
							k.format = k.format || "html";
							i.processObj = k;
							l = l.cloneNode(true);
							i.key = "" + (parseInt(i.key) + 1);
							if (!k.no_events) {
								k.node = l;
								i.onPreProcess.dispatch(i, k)
							}
							i.writer.reset();
							i._serializeNode(l, k.getInner);
							k.content = i.writer.getContent();
							if (!k.no_events) {
								i.onPostProcess.dispatch(i, k)
							}
							i._postProcess(k);
							k.node = null;
							return e.trim(k.content)
						},
						_postProcess : function(n) {
							var i = this, k = i.settings, j = n.content, m = [], l;
							if (n.format == "html") {
								l = i
										._protect( {
											content :j,
											patterns : [
													{
														pattern :/(<script[^>]*>)(.*?)(<\/script>)/g
													},
													{
														pattern :/(<style[^>]*>)(.*?)(<\/style>)/g
													},
													{
														pattern :/(<pre[^>]*>)(.*?)(<\/pre>)/g,
														encode :1
													},
													{
														pattern :/(<!--\[CDATA\[)(.*?)(\]\]-->)/g
													} ]
										});
								j = l.content;
								if (k.entity_encoding !== "raw") {
									j = i._encode(j)
								}
								if (!n.set) {
									j = j
											.replace(
													/<p>\s+<\/p>|<p([^>]+)>\s+<\/p>/g,
													k.entity_encoding == "numeric" ? "<p$1>&#160;</p>"
															: "<p$1>&nbsp;</p>");
									if (k.remove_linebreaks) {
										j = j.replace(/\r?\n|\r/g, " ");
										j = j.replace(/(<[^>]+>)\s+/g, "$1 ");
										j = j.replace(/\s+(<\/[^>]+>)/g, " $1");
										j = j
												.replace(
														/<(p|h[1-6]|blockquote|hr|div|table|tbody|tr|td|body|head|html|title|meta|style|pre|script|link|object) ([^>]+)>\s+/g,
														"<$1 $2>");
										j = j
												.replace(
														/<(p|h[1-6]|blockquote|hr|div|table|tbody|tr|td|body|head|html|title|meta|style|pre|script|link|object)>\s+/g,
														"<$1>");
										j = j
												.replace(
														/\s+<\/(p|h[1-6]|blockquote|hr|div|table|tbody|tr|td|body|head|html|title|meta|style|pre|script|link|object)>/g,
														"</$1>")
									}
									if (k.apply_source_formatting
											&& k.indent_mode == "simple") {
										j = j
												.replace(
														/<(\/?)(ul|hr|table|meta|link|tbody|tr|object|body|head|html|map)(|[^>]+)>\s*/g,
														"\n<$1$2$3>\n");
										j = j
												.replace(
														/\s*<(p|h[1-6]|blockquote|div|title|style|pre|script|td|li|area)(|[^>]+)>/g,
														"\n<$1$2>");
										j = j
												.replace(
														/<\/(p|h[1-6]|blockquote|div|title|style|pre|script|td|li)>\s*/g,
														"</$1>\n");
										j = j.replace(/\n\n/g, "\n")
									}
								}
								j = i._unprotect(j, l);
								j = j.replace(/<!--\[CDATA\[([\s\S]+)\]\]-->/g,
										"<![CDATA[$1]]>");
								if (k.entity_encoding == "raw") {
									j = j
											.replace(
													/<p>&nbsp;<\/p>|<p([^>]+)>&nbsp;<\/p>/g,
													"<p$1>\u00a0</p>")
								}
							}
							n.content = j
						},
						_serializeNode : function(C, m) {
							var y = this, z = y.settings, u = y.writer, p, j, r, E, D, F, A, h, x, k, q, B, o;
							if (!z.node_filter || z.node_filter(C)) {
								switch (C.nodeType) {
								case 1:
									if (C.hasAttribute ? C
											.hasAttribute("mce_bogus") : C
											.getAttribute("mce_bogus")) {
										return
									}
									o = false;
									p = C.hasChildNodes();
									k = C.getAttribute("mce_name")
											|| C.nodeName.toLowerCase();
									if (d) {
										if (C.scopeName !== "HTML"
												&& C.scopeName !== "html") {
											k = C.scopeName + ":" + k
										}
									}
									if (k.indexOf("mce:") === 0) {
										k = k.substring(4)
									}
									if (!y.validElementsRE.test(k)
											|| (y.invalidElementsRE && y.invalidElementsRE
													.test(k)) || m) {
										o = true;
										break
									}
									if (d) {
										if (z.fix_content_duplication) {
											if (C.mce_serialized == y.key) {
												return
											}
											C.mce_serialized = y.key
										}
										if (k.charAt(0) == "/") {
											k = k.substring(1)
										}
									} else {
										if (a) {
											if (C.nodeName === "BR"
													&& C.getAttribute("type") == "_moz") {
												return
											}
										}
									}
									if (y.childRules) {
										if (y.parentElementsRE
												.test(y.elementName)) {
											if (!y.childRules[y.elementName]
													.test(k)) {
												o = true;
												break
											}
										}
										y.elementName = k
									}
									q = y.findRule(k);
									k = q.name || k;
									if ((!p && q.noEmpty) || (d && !k)) {
										o = true;
										break
									}
									if (q.requiredAttribs) {
										F = q.requiredAttribs;
										for (E = F.length - 1; E >= 0; E--) {
											if (this.dom.getAttrib(C, F[E]) !== "") {
												break
											}
										}
										if (E == -1) {
											o = true;
											break
										}
									}
									u.writeStartElement(k);
									if (q.attribs) {
										for (E = 0, A = q.attribs, D = A.length; E < D; E++) {
											F = A[E];
											x = y._getAttrib(C, F);
											if (x !== null) {
												u.writeAttribute(F.name, x)
											}
										}
									}
									if (q.validAttribsRE) {
										A = y.dom.getAttribs(C);
										for (E = A.length - 1; E > -1; E--) {
											h = A[E];
											if (h.specified) {
												F = h.nodeName.toLowerCase();
												if (z.invalid_attrs.test(F)
														|| !q.validAttribsRE
																.test(F)) {
													continue
												}
												B = y.findAttribRule(q, F);
												x = y._getAttrib(C, B, F);
												if (x !== null) {
													u.writeAttribute(F, x)
												}
											}
										}
									}
									if (q.padd) {
										if (p && (r = C.firstChild)
												&& r.nodeType === 1
												&& C.childNodes.length === 1) {
											if (r.hasAttribute ? r
													.hasAttribute("mce_bogus")
													: r
															.getAttribute("mce_bogus")) {
												u.writeText("\u00a0")
											}
										} else {
											if (!p) {
												u.writeText("\u00a0")
											}
										}
									}
									break;
								case 3:
									if (y.childRules
											&& y.parentElementsRE
													.test(y.elementName)) {
										if (!y.childRules[y.elementName]
												.test(C.nodeName)) {
											return
										}
									}
									return u.writeText(C.nodeValue);
								case 4:
									return u.writeCDATA(C.nodeValue);
								case 8:
									return u.writeComment(C.nodeValue)
								}
							} else {
								if (C.nodeType == 1) {
									p = C.hasChildNodes()
								}
							}
							if (p) {
								r = C.firstChild;
								while (r) {
									y._serializeNode(r);
									y.elementName = k;
									r = r.nextSibling
								}
							}
							if (!o) {
								if (p || !z.closed.test(k)) {
									u.writeFullEndElement()
								} else {
									u.writeEndElement()
								}
							}
						},
						_protect : function(j) {
							var i = this;
							j.items = j.items || [];
							function h(l) {
								return l.replace(/[\r\n\\]/g, function(m) {
									if (m === "\n") {
										return "\\n"
									} else {
										if (m === "\\") {
											return "\\\\"
										}
									}
									return "\\r"
								})
							}
							function k(l) {
								return l.replace(/\\[\\rn]/g, function(m) {
									if (m === "\\n") {
										return "\n"
									} else {
										if (m === "\\\\") {
											return "\\"
										}
									}
									return "\r"
								})
							}
							f(j.patterns, function(l) {
								j.content = k(h(j.content).replace(
										l.pattern,
										function(n, o, m, p) {
											m = k(m);
											if (l.encode) {
												m = i._encode(m)
											}
											j.items.push(m);
											return o + "<!--mce:"
													+ (j.items.length - 1)
													+ "-->" + p
										}))
							});
							return j
						},
						_unprotect : function(i, j) {
							i = i.replace(/\<!--mce:([0-9]+)--\>/g, function(k,
									h) {
								return j.items[parseInt(h)]
							});
							j.items = [];
							return i
						},
						_encode : function(m) {
							var j = this, k = j.settings, i;
							if (k.entity_encoding !== "raw") {
								if (k.entity_encoding.indexOf("named") != -1) {
									j.setEntities(k.entities);
									i = j.entityLookup;
									m = m.replace(j.entitiesRE, function(h) {
										var l;
										if (l = i[h]) {
											h = "&" + l + ";"
										}
										return h
									})
								}
								if (k.entity_encoding.indexOf("numeric") != -1) {
									m = m.replace(/[\u007E-\uFFFF]/g, function(
											h) {
										return "&#" + h.charCodeAt(0) + ";"
									})
								}
							}
							return m
						},
						_setup : function() {
							var h = this, i = this.settings;
							if (h.done) {
								return
							}
							h.done = 1;
							h.setRules(i.valid_elements);
							h.addRules(i.extended_valid_elements);
							h.addValidChildRules(i.valid_child_elements);
							if (i.invalid_elements) {
								h.invalidElementsRE = new RegExp("^("
										+ c(i.invalid_elements.replace(/,/g,
												"|").toLowerCase()) + ")$")
							}
							if (i.attrib_value_filter) {
								h.attribValueFilter = i.attribValueFilter
							}
						},
						_getAttrib : function(m, j, h) {
							var l, k;
							h = h || j.name;
							if (j.forcedVal && (k = j.forcedVal)) {
								if (k === "{$uid}") {
									return this.dom.uniqueId()
								}
								return k
							}
							k = this.dom.getAttrib(m, h);
							if (this.settings.bool_attrs.test(h) && k) {
								k = ("" + k).toLowerCase();
								if (k === "false" || k === "0") {
									return null
								}
								k = h
							}
							switch (h) {
							case "rowspan":
							case "colspan":
								if (k == "1") {
									k = ""
								}
								break
							}
							if (this.attribValueFilter) {
								k = this.attribValueFilter(h, k, m)
							}
							if (j.validVals) {
								for (l = j.validVals.length - 1; l >= 0; l--) {
									if (k == j.validVals[l]) {
										break
									}
								}
								if (l == -1) {
									return null
								}
							}
							if (k === ""
									&& typeof (j.defaultVal) != "undefined") {
								k = j.defaultVal;
								if (k === "{$uid}") {
									return this.dom.uniqueId()
								}
								return k
							} else {
								if (h == "class" && this.processObj.get) {
									k = k.replace(/\s?mceItem\w+\s?/g, "")
								}
							}
							if (k === "") {
								return null
							}
							return k
						}
					})
})(tinymce);
( function(tinymce) {
	var each = tinymce.each, Event = tinymce.dom.Event;
	tinymce
			.create(
					"tinymce.dom.ScriptLoader",
					{
						ScriptLoader : function(s) {
							this.settings = s || {};
							this.queue = [];
							this.lookup = {}
						},
						isDone : function(u) {
							return this.lookup[u] ? this.lookup[u].state == 2
									: 0
						},
						markDone : function(u) {
							this.lookup[u] = {
								state :2,
								url :u
							}
						},
						add : function(u, cb, s, pr) {
							var t = this, lo = t.lookup, o;
							if (o = lo[u]) {
								if (cb && o.state == 2) {
									cb.call(s || this)
								}
								return o
							}
							o = {
								state :0,
								url :u,
								func :cb,
								scope :s || this
							};
							if (pr) {
								t.queue.unshift(o)
							} else {
								t.queue.push(o)
							}
							lo[u] = o;
							return o
						},
						load : function(u, cb, s) {
							var t = this, o;
							if (o = t.lookup[u]) {
								if (cb && o.state == 2) {
									cb.call(s || t)
								}
								return o
							}
							function loadScript(u) {
								if (Event.domLoaded || t.settings.strict_mode) {
									tinymce.util.XHR.send( {
										url :tinymce._addVer(u),
										error :t.settings.error,
										async :false,
										success : function(co) {
											t.eval(co)
										}
									})
								} else {
									document
											.write('<script type="text/javascript" src="' + tinymce
													._addVer(u) + '"><\/script>')
								}
							}
							if (!tinymce.is(u, "string")) {
								each(u, function(u) {
									loadScript(u)
								});
								if (cb) {
									cb.call(s || t)
								}
							} else {
								loadScript(u);
								if (cb) {
									cb.call(s || t)
								}
							}
						},
						loadQueue : function(cb, s) {
							var t = this;
							if (!t.queueLoading) {
								t.queueLoading = 1;
								t.queueCallbacks = [];
								t.loadScripts(t.queue, function() {
									t.queueLoading = 0;
									if (cb) {
										cb.call(s || t)
									}
									each(t.queueCallbacks, function(o) {
										o.func.call(o.scope)
									})
								})
							} else {
								if (cb) {
									t.queueCallbacks.push( {
										func :cb,
										scope :s || t
									})
								}
							}
						},
						eval : function(co) {
							var w = window;
							if (!w.execScript) {
								try {
									eval.call(w, co)
								} catch (ex) {
									eval(co, w)
								}
							} else {
								w.execScript(co)
							}
						},
						loadScripts : function(sc, cb, s) {
							var t = this, lo = t.lookup;
							function done(o) {
								o.state = 2;
								if (o.func) {
									o.func.call(o.scope || t)
								}
							}
							function allDone() {
								var l;
								l = sc.length;
								each(sc, function(o) {
									o = lo[o.url];
									if (o.state === 2) {
										done(o);
										l--
									} else {
										load(o)
									}
								});
								if (l === 0 && cb) {
									cb.call(s || t);
									cb = 0
								}
							}
							function load(o) {
								if (o.state > 0) {
									return
								}
								o.state = 1;
								tinymce.dom.ScriptLoader.loadScript(o.url,
										function() {
											done(o);
											allDone()
										})
							}
							each(
									sc,
									function(o) {
										var u = o.url;
										if (!lo[u]) {
											lo[u] = o;
											t.queue.push(o)
										} else {
											o = lo[u]
										}
										if (o.state > 0) {
											return
										}
										if (!Event.domLoaded
												&& !t.settings.strict_mode) {
											var ix, ol = "";
											if (cb || o.func) {
												o.state = 1;
												ix = tinymce.dom.ScriptLoader
														._addOnLoad( function() {
															done(o);
															allDone()
														});
												if (tinymce.isIE) {
													ol = ' onreadystatechange="'
												} else {
													ol = ' onload="'
												}
												ol += "tinymce.dom.ScriptLoader._onLoad(this,'"
														+ u + "'," + ix + ');"'
											}
											document
													.write('<script type="text/javascript" src="'
															+ tinymce
																	._addVer(u)
															+ '"'
															+ ol
															+ "><\/script>");
											if (!o.func) {
												done(o)
											}
										} else {
											load(o)
										}
									});
							allDone()
						},
						"static" : {
							_addOnLoad : function(f) {
								var t = this;
								t._funcs = t._funcs || [];
								t._funcs.push(f);
								return t._funcs.length - 1
							},
							_onLoad : function(e, u, ix) {
								if (!tinymce.isIE || e.readyState == "complete") {
									this._funcs[ix].call(this)
								}
							},
							loadScript : function(u, cb) {
								var id = tinymce.DOM.uniqueId(), e;
								function done() {
									Event.clear(id);
									tinymce.DOM.remove(id);
									if (cb) {
										cb.call(document, u);
										cb = 0
									}
								}
								if (tinymce.isIE) {
									tinymce.util.XHR.send( {
										url :tinymce._addVer(u),
										async :false,
										success : function(co) {
											window.execScript(co);
											done()
										}
									})
								} else {
									e = tinymce.DOM.create("script", {
										id :id,
										type :"text/javascript",
										src :tinymce._addVer(u)
									});
									Event.add(e, "load", done);
									(document.getElementsByTagName("head")[0] || document.body)
											.appendChild(e)
								}
							}
						}
					});
	tinymce.ScriptLoader = new tinymce.dom.ScriptLoader()
})(tinymce);
( function(c) {
	var b = c.DOM, a = c.is;
	c.create("tinymce.ui.Control", {
		Control : function(e, d) {
			this.id = e;
			this.settings = d = d || {};
			this.rendered = false;
			this.onRender = new c.util.Dispatcher(this);
			this.classPrefix = "";
			this.scope = d.scope || this;
			this.disabled = 0;
			this.active = 0
		},
		setDisabled : function(d) {
			var f;
			if (d != this.disabled) {
				f = b.get(this.id);
				if (f && this.settings.unavailable_prefix) {
					if (d) {
						this.prevTitle = f.title;
						f.title = this.settings.unavailable_prefix + ": "
								+ f.title
					} else {
						f.title = this.prevTitle
					}
				}
				this.setState("Disabled", d);
				this.setState("Enabled", !d);
				this.disabled = d
			}
		},
		isDisabled : function() {
			return this.disabled
		},
		setActive : function(d) {
			if (d != this.active) {
				this.setState("Active", d);
				this.active = d
			}
		},
		isActive : function() {
			return this.active
		},
		setState : function(f, d) {
			var e = b.get(this.id);
			f = this.classPrefix + f;
			if (d) {
				b.addClass(e, f)
			} else {
				b.removeClass(e, f)
			}
		},
		isRendered : function() {
			return this.rendered
		},
		renderHTML : function() {
		},
		renderTo : function(d) {
			b.setHTML(d, this.renderHTML())
		},
		postRender : function() {
			var e = this, d;
			if (a(e.disabled)) {
				d = e.disabled;
				e.disabled = -1;
				e.setDisabled(d)
			}
			if (a(e.active)) {
				d = e.active;
				e.active = -1;
				e.setActive(d)
			}
		},
		remove : function() {
			b.remove(this.id);
			this.destroy()
		},
		destroy : function() {
			c.dom.Event.clear(this.id)
		}
	})
})(tinymce);
tinymce.create("tinymce.ui.Container:tinymce.ui.Control", {
	Container : function(b, a) {
		this.parent(b, a);
		this.controls = [];
		this.lookup = {}
	},
	add : function(a) {
		this.lookup[a.id] = a;
		this.controls.push(a);
		return a
	},
	get : function(a) {
		return this.lookup[a]
	}
});
tinymce.create("tinymce.ui.Separator:tinymce.ui.Control", {
	Separator : function(b, a) {
		this.parent(b, a);
		this.classPrefix = "mceSeparator"
	},
	renderHTML : function() {
		return tinymce.DOM.createHTML("span", {
			"class" :this.classPrefix
		})
	}
});
( function(d) {
	var c = d.is, b = d.DOM, e = d.each, a = d.walk;
	d.create("tinymce.ui.MenuItem:tinymce.ui.Control", {
		MenuItem : function(g, f) {
			this.parent(g, f);
			this.classPrefix = "mceMenuItem"
		},
		setSelected : function(f) {
			this.setState("Selected", f);
			this.selected = f
		},
		isSelected : function() {
			return this.selected
		},
		postRender : function() {
			var f = this;
			f.parent();
			if (c(f.selected)) {
				f.setSelected(f.selected)
			}
		}
	})
})(tinymce);
( function(d) {
	var c = d.is, b = d.DOM, e = d.each, a = d.walk;
	d.create("tinymce.ui.Menu:tinymce.ui.MenuItem", {
		Menu : function(h, g) {
			var f = this;
			f.parent(h, g);
			f.items = {};
			f.collapsed = false;
			f.menuCount = 0;
			f.onAddItem = new d.util.Dispatcher(this)
		},
		expand : function(g) {
			var f = this;
			if (g) {
				a(f, function(h) {
					if (h.expand) {
						h.expand()
					}
				}, "items", f)
			}
			f.collapsed = false
		},
		collapse : function(g) {
			var f = this;
			if (g) {
				a(f, function(h) {
					if (h.collapse) {
						h.collapse()
					}
				}, "items", f)
			}
			f.collapsed = true
		},
		isCollapsed : function() {
			return this.collapsed
		},
		add : function(f) {
			if (!f.settings) {
				f = new d.ui.MenuItem(f.id || b.uniqueId(), f)
			}
			this.onAddItem.dispatch(this, f);
			return this.items[f.id] = f
		},
		addSeparator : function() {
			return this.add( {
				separator :true
			})
		},
		addMenu : function(f) {
			if (!f.collapse) {
				f = this.createMenu(f)
			}
			this.menuCount++;
			return this.add(f)
		},
		hasMenus : function() {
			return this.menuCount !== 0
		},
		remove : function(f) {
			delete this.items[f.id]
		},
		removeAll : function() {
			var f = this;
			a(f, function(g) {
				if (g.removeAll) {
					g.removeAll()
				} else {
					g.remove()
				}
				g.destroy()
			}, "items", f);
			f.items = {}
		},
		createMenu : function(g) {
			var f = new d.ui.Menu(g.id || b.uniqueId(), g);
			f.onAddItem.add(this.onAddItem.dispatch, this.onAddItem);
			return f
		}
	})
})(tinymce);
( function(e) {
	var d = e.is, c = e.DOM, f = e.each, a = e.dom.Event, b = e.dom.Element;
	e
			.create(
					"tinymce.ui.DropMenu:tinymce.ui.Menu",
					{
						DropMenu : function(h, g) {
							g = g || {};
							g.container = g.container || c.doc.body;
							g.offset_x = g.offset_x || 0;
							g.offset_y = g.offset_y || 0;
							g.vp_offset_x = g.vp_offset_x || 0;
							g.vp_offset_y = g.vp_offset_y || 0;
							if (d(g.icons) && !g.icons) {
								g["class"] += " mceNoIcons"
							}
							this.parent(h, g);
							this.onShowMenu = new e.util.Dispatcher(this);
							this.onHideMenu = new e.util.Dispatcher(this);
							this.classPrefix = "mceMenu"
						},
						createMenu : function(j) {
							var h = this, i = h.settings, g;
							j.container = j.container || i.container;
							j.parent = h;
							j.constrain = j.constrain || i.constrain;
							j["class"] = j["class"] || i["class"];
							j.vp_offset_x = j.vp_offset_x || i.vp_offset_x;
							j.vp_offset_y = j.vp_offset_y || i.vp_offset_y;
							g = new e.ui.DropMenu(j.id || c.uniqueId(), j);
							g.onAddItem.add(h.onAddItem.dispatch, h.onAddItem);
							return g
						},
						update : function() {
							var i = this, j = i.settings, g = c.get("menu_"
									+ i.id + "_tbl"), l = c.get("menu_" + i.id
									+ "_co"), h, k;
							h = j.max_width ? Math.min(g.clientWidth,
									j.max_width) : g.clientWidth;
							k = j.max_height ? Math.min(g.clientHeight,
									j.max_height) : g.clientHeight;
							if (!c.boxModel) {
								i.element.setStyles( {
									width :h + 2,
									height :k + 2
								})
							} else {
								i.element.setStyles( {
									width :h,
									height :k
								})
							}
							if (j.max_width) {
								c.setStyle(l, "width", h)
							}
							if (j.max_height) {
								c.setStyle(l, "height", k);
								if (g.clientHeight < j.max_height) {
									c.setStyle(l, "overflow", "hidden")
								}
							}
						},
						showMenu : function(p, n, r) {
							var z = this, A = z.settings, o, g = c
									.getViewPort(), u, l, v, q, i = 2, k, j, m = z.classPrefix;
							z.collapse(1);
							if (z.isMenuVisible) {
								return
							}
							if (!z.rendered) {
								o = c.add(z.settings.container, z.renderNode());
								f(z.items, function(h) {
									h.postRender()
								});
								z.element = new b("menu_" + z.id, {
									blocker :1,
									container :A.container
								})
							} else {
								o = c.get("menu_" + z.id)
							}
							if (!e.isOpera) {
								c.setStyles(o, {
									left :-65535,
									top :-65535
								})
							}
							c.show(o);
							z.update();
							p += A.offset_x || 0;
							n += A.offset_y || 0;
							g.w -= 4;
							g.h -= 4;
							if (A.constrain) {
								u = o.clientWidth - i;
								l = o.clientHeight - i;
								v = g.x + g.w;
								q = g.y + g.h;
								if ((p + A.vp_offset_x + u) > v) {
									p = r ? r - u : Math.max(0,
											(v - A.vp_offset_x) - u)
								}
								if ((n + A.vp_offset_y + l) > q) {
									n = Math.max(0, (q - A.vp_offset_y) - l)
								}
							}
							c.setStyles(o, {
								left :p,
								top :n
							});
							z.element.update();
							z.isMenuVisible = 1;
							z.mouseClickFunc = a.add(o, "click", function(s) {
								var h;
								s = s.target;
								if (s && (s = c.getParent(s, "TR"))
										&& !c.hasClass(s, m + "ItemSub")) {
									h = z.items[s.id];
									if (h.isDisabled()) {
										return
									}
									k = z;
									while (k) {
										if (k.hideMenu) {
											k.hideMenu()
										}
										k = k.settings.parent
									}
									if (h.settings.onclick) {
										h.settings.onclick(s)
									}
									return a.cancel(s)
								}
							});
							if (z.hasMenus()) {
								z.mouseOverFunc = a
										.add(
												o,
												"mouseover",
												function(w) {
													var h, t, s;
													w = w.target;
													if (w
															&& (w = c
																	.getParent(
																			w,
																			"TR"))) {
														h = z.items[w.id];
														if (z.lastMenu) {
															z.lastMenu
																	.collapse(1)
														}
														if (h.isDisabled()) {
															return
														}
														if (w
																&& c
																		.hasClass(
																				w,
																				m
																						+ "ItemSub")) {
															t = c.getRect(w);
															h.showMenu((t.x
																	+ t.w - i),
																	t.y - i,
																	t.x);
															z.lastMenu = h;
															c
																	.addClass(
																			c
																					.get(h.id).firstChild,
																			m
																					+ "ItemActive")
														}
													}
												})
							}
							z.onShowMenu.dispatch(z);
							if (A.keyboard_focus) {
								a.add(o, "keydown", z._keyHandler, z);
								c.select("a", "menu_" + z.id)[0].focus();
								z._focusIdx = 0
							}
						},
						hideMenu : function(j) {
							var g = this, i = c.get("menu_" + g.id), h;
							if (!g.isMenuVisible) {
								return
							}
							a.remove(i, "mouseover", g.mouseOverFunc);
							a.remove(i, "click", g.mouseClickFunc);
							a.remove(i, "keydown", g._keyHandler);
							c.hide(i);
							g.isMenuVisible = 0;
							if (!j) {
								g.collapse(1)
							}
							if (g.element) {
								g.element.hide()
							}
							if (h = c.get(g.id)) {
								c.removeClass(h.firstChild, g.classPrefix
										+ "ItemActive")
							}
							g.onHideMenu.dispatch(g)
						},
						add : function(i) {
							var g = this, h;
							i = g.parent(i);
							if (g.isRendered && (h = c.get("menu_" + g.id))) {
								g._add(c.select("tbody", h)[0], i)
							}
							return i
						},
						collapse : function(g) {
							this.parent(g);
							this.hideMenu(1)
						},
						remove : function(g) {
							c.remove(g.id);
							this.destroy();
							return this.parent(g)
						},
						destroy : function() {
							var g = this, h = c.get("menu_" + g.id);
							a.remove(h, "mouseover", g.mouseOverFunc);
							a.remove(h, "click", g.mouseClickFunc);
							if (g.element) {
								g.element.remove()
							}
							c.remove(h)
						},
						renderNode : function() {
							var i = this, j = i.settings, l, h, k, g;
							g = c
									.create(
											"div",
											{
												id :"menu_" + i.id,
												"class" :j["class"],
												style :"position:absolute;left:0;top:0;z-index:200000"
											});
							k = c.add(g, "div", {
								id :"menu_" + i.id + "_co",
								"class" :i.classPrefix
										+ (j["class"] ? " " + j["class"] : "")
							});
							i.element = new b("menu_" + i.id, {
								blocker :1,
								container :j.container
							});
							if (j.menu_line) {
								c.add(k, "span", {
									"class" :i.classPrefix + "Line"
								})
							}
							l = c.add(k, "table", {
								id :"menu_" + i.id + "_tbl",
								border :0,
								cellPadding :0,
								cellSpacing :0
							});
							h = c.add(l, "tbody");
							f(i.items, function(m) {
								i._add(h, m)
							});
							i.rendered = true;
							return g
						},
						_keyHandler : function(j) {
							var i = this, h = j.keyCode;
							function g(m) {
								var k = i._focusIdx + m, l = c.select("a",
										"menu_" + i.id)[k];
								if (l) {
									i._focusIdx = k;
									l.focus()
								}
							}
							switch (h) {
							case 38:
								g(-1);
								return;
							case 40:
								g(1);
								return;
							case 13:
								return;
							case 27:
								return this.hideMenu()
							}
						},
						_add : function(j, h) {
							var i, q = h.settings, p, l, k, m = this.classPrefix, g;
							if (q.separator) {
								l = c.add(j, "tr", {
									id :h.id,
									"class" :m + "ItemSeparator"
								});
								c.add(l, "td", {
									"class" :m + "ItemSeparator"
								});
								if (i = l.previousSibling) {
									c.addClass(i, "mceLast")
								}
								return
							}
							i = l = c.add(j, "tr", {
								id :h.id,
								"class" :m + "Item " + m + "ItemEnabled"
							});
							i = k = c.add(i, "td");
							i = p = c.add(i, "a", {
								href :"javascript:;",
								onclick :"return false;",
								onmousedown :"return false;"
							});
							c.addClass(k, q["class"]);
							g = c.add(i, "span", {
								"class" :"mceIcon"
										+ (q.icon ? " mce_" + q.icon : "")
							});
							if (q.icon_src) {
								c.add(g, "img", {
									src :q.icon_src
								})
							}
							i = c.add(i, q.element || "span", {
								"class" :"mceText",
								title :h.settings.title
							}, h.settings.title);
							if (h.settings.style) {
								c.setAttrib(i, "style", h.settings.style)
							}
							if (j.childNodes.length == 1) {
								c.addClass(l, "mceFirst")
							}
							if ((i = l.previousSibling)
									&& c.hasClass(i, m + "ItemSeparator")) {
								c.addClass(l, "mceFirst")
							}
							if (h.collapse) {
								c.addClass(l, m + "ItemSub")
							}
							if (i = l.previousSibling) {
								c.removeClass(i, "mceLast")
							}
							c.addClass(l, "mceLast")
						}
					})
})(tinymce);
( function(b) {
	var a = b.DOM;
	b
			.create(
					"tinymce.ui.Button:tinymce.ui.Control",
					{
						Button : function(d, c) {
							this.parent(d, c);
							this.classPrefix = "mceButton"
						},
						renderHTML : function() {
							var f = this.classPrefix, e = this.settings, d, c;
							c = a.encode(e.label || "");
							d = '<a id="'
									+ this.id
									+ '" href="javascript:;" class="'
									+ f
									+ " "
									+ f
									+ "Enabled "
									+ e["class"]
									+ (c ? " " + f + "Labeled" : "")
									+ '" onmousedown="return false;" onclick="return false;" title="'
									+ a.encode(e.title) + '">';
							if (e.image) {
								d += '<img class="mceIcon" src="' + e.image
										+ '" />' + c + "</a>"
							} else {
								d += '<span class="mceIcon '
										+ e["class"]
										+ '"></span>'
										+ (c ? '<span class="' + f + 'Label">'
												+ c + "</span>" : "") + "</a>"
							}
							return d
						},
						postRender : function() {
							var c = this, d = c.settings;
							b.dom.Event.add(c.id, "click", function(f) {
								if (!c.isDisabled()) {
									return d.onclick.call(d.scope, f)
								}
							})
						}
					})
})(tinymce);
( function(d) {
	var c = d.DOM, b = d.dom.Event, e = d.each, a = d.util.Dispatcher;
	d.create("tinymce.ui.ListBox:tinymce.ui.Control", {
		ListBox : function(h, g) {
			var f = this;
			f.parent(h, g);
			f.items = [];
			f.onChange = new a(f);
			f.onPostRender = new a(f);
			f.onAdd = new a(f);
			f.onRenderMenu = new d.util.Dispatcher(this);
			f.classPrefix = "mceListBox"
		},
		select : function(h) {
			var g = this, j, i;
			if (h == undefined) {
				return g.selectByIndex(-1)
			}
			if (h && h.call) {
				i = h
			} else {
				i = function(f) {
					return f == h
				}
			}
			if (h != g.selectedValue) {
				e(g.items, function(k, f) {
					if (i(k.value)) {
						j = 1;
						g.selectByIndex(f);
						return false
					}
				});
				if (!j) {
					g.selectByIndex(-1)
				}
			}
		},
		selectByIndex : function(f) {
			var g = this, h, i;
			if (f != g.selectedIndex) {
				h = c.get(g.id + "_text");
				i = g.items[f];
				if (i) {
					g.selectedValue = i.value;
					g.selectedIndex = f;
					c.setHTML(h, c.encode(i.title));
					c.removeClass(h, "mceTitle")
				} else {
					c.setHTML(h, c.encode(g.settings.title));
					c.addClass(h, "mceTitle");
					g.selectedValue = g.selectedIndex = null
				}
				h = 0
			}
		},
		add : function(i, f, h) {
			var g = this;
			h = h || {};
			h = d.extend(h, {
				title :i,
				value :f
			});
			g.items.push(h);
			g.onAdd.dispatch(g, h)
		},
		getLength : function() {
			return this.items.length
		},
		renderHTML : function() {
			var i = "", f = this, g = f.settings, j = f.classPrefix;
			i = '<table id="' + f.id
					+ '" cellpadding="0" cellspacing="0" class="' + j + " " + j
					+ "Enabled" + (g["class"] ? (" " + g["class"]) : "")
					+ '"><tbody><tr>';
			i += "<td>" + c.createHTML("a", {
				id :f.id + "_text",
				href :"javascript:;",
				"class" :"mceText",
				onclick :"return false;",
				onmousedown :"return false;"
			}, c.encode(f.settings.title)) + "</td>";
			i += "<td>" + c.createHTML("a", {
				id :f.id + "_open",
				tabindex :-1,
				href :"javascript:;",
				"class" :"mceOpen",
				onclick :"return false;",
				onmousedown :"return false;"
			}, "<span></span>") + "</td>";
			i += "</tr></tbody></table>";
			return i
		},
		showMenu : function() {
			var g = this, j, i, h = c.get(this.id), f;
			if (g.isDisabled() || g.items.length == 0) {
				return
			}
			if (g.menu && g.menu.isMenuVisible) {
				return g.hideMenu()
			}
			if (!g.isMenuRendered) {
				g.renderMenu();
				g.isMenuRendered = true
			}
			j = c.getPos(this.settings.menu_container);
			i = c.getPos(h);
			f = g.menu;
			f.settings.offset_x = i.x;
			f.settings.offset_y = i.y;
			f.settings.keyboard_focus = !d.isOpera;
			if (g.oldID) {
				f.items[g.oldID].setSelected(0)
			}
			e(g.items, function(k) {
				if (k.value === g.selectedValue) {
					f.items[k.id].setSelected(1);
					g.oldID = k.id
				}
			});
			f.showMenu(0, h.clientHeight);
			b.add(c.doc, "mousedown", g.hideMenu, g);
			c.addClass(g.id, g.classPrefix + "Selected")
		},
		hideMenu : function(g) {
			var f = this;
			if (g
					&& g.type == "mousedown"
					&& (g.target.id == f.id + "_text" || g.target.id == f.id
							+ "_open")) {
				return
			}
			if (!g || !c.getParent(g.target, ".mceMenu")) {
				c.removeClass(f.id, f.classPrefix + "Selected");
				b.remove(c.doc, "mousedown", f.hideMenu, f);
				if (f.menu) {
					f.menu.hideMenu()
				}
			}
		},
		renderMenu : function() {
			var g = this, f;
			f = g.settings.control_manager.createDropMenu(g.id + "_menu", {
				menu_line :1,
				"class" :g.classPrefix + "Menu mceNoIcons",
				max_width :150,
				max_height :150
			});
			f.onHideMenu.add(g.hideMenu, g);
			f.add( {
				title :g.settings.title,
				"class" :"mceMenuItemTitle",
				onclick : function() {
					if (g.settings.onselect("") !== false) {
						g.select("")
					}
				}
			});
			e(g.items, function(h) {
				h.id = c.uniqueId();
				h.onclick = function() {
					if (g.settings.onselect(h.value) !== false) {
						g.select(h.value)
					}
				};
				f.add(h)
			});
			g.onRenderMenu.dispatch(g, f);
			g.menu = f
		},
		postRender : function() {
			var f = this, g = f.classPrefix;
			b.add(f.id, "click", f.showMenu, f);
			b.add(f.id + "_text", "focus", function(h) {
				if (!f._focused) {
					f.keyDownHandler = b.add(f.id + "_text", "keydown",
							function(l) {
								var i = -1, j, k = l.keyCode;
								e(f.items, function(m, n) {
									if (f.selectedValue == m.value) {
										i = n
									}
								});
								if (k == 38) {
									j = f.items[i - 1]
								} else {
									if (k == 40) {
										j = f.items[i + 1]
									} else {
										if (k == 13) {
											j = f.selectedValue;
											f.selectedValue = null;
											f.settings.onselect(j);
											return b.cancel(l)
										}
									}
								}
								if (j) {
									f.hideMenu();
									f.select(j.value)
								}
							})
				}
				f._focused = 1
			});
			b.add(f.id + "_text", "blur", function() {
				b.remove(f.id + "_text", "keydown", f.keyDownHandler);
				f._focused = 0
			});
			if (d.isIE6 || !c.boxModel) {
				b.add(f.id, "mouseover", function() {
					if (!c.hasClass(f.id, g + "Disabled")) {
						c.addClass(f.id, g + "Hover")
					}
				});
				b.add(f.id, "mouseout", function() {
					if (!c.hasClass(f.id, g + "Disabled")) {
						c.removeClass(f.id, g + "Hover")
					}
				})
			}
			f.onPostRender.dispatch(f, c.get(f.id))
		},
		destroy : function() {
			this.parent();
			b.clear(this.id + "_text")
		}
	})
})(tinymce);
( function(d) {
	var c = d.DOM, b = d.dom.Event, e = d.each, a = d.util.Dispatcher;
	d.create("tinymce.ui.NativeListBox:tinymce.ui.ListBox", {
		NativeListBox : function(g, f) {
			this.parent(g, f);
			this.classPrefix = "mceNativeListBox"
		},
		setDisabled : function(f) {
			c.get(this.id).disabled = f
		},
		isDisabled : function() {
			return c.get(this.id).disabled
		},
		select : function(h) {
			var g = this, j, i;
			if (h == undefined) {
				return g.selectByIndex(-1)
			}
			if (h && h.call) {
				i = h
			} else {
				i = function(f) {
					return f == h
				}
			}
			if (h != g.selectedValue) {
				e(g.items, function(k, f) {
					if (i(k.value)) {
						j = 1;
						g.selectByIndex(f);
						return false
					}
				});
				if (!j) {
					g.selectByIndex(-1)
				}
			}
		},
		selectByIndex : function(f) {
			c.get(this.id).selectedIndex = f + 1;
			this.selectedValue = this.items[f] ? this.items[f].value : null
		},
		add : function(j, g, f) {
			var i, h = this;
			f = f || {};
			f.value = g;
			if (h.isRendered()) {
				c.add(c.get(this.id), "option", f, j)
			}
			i = {
				title :j,
				value :g,
				attribs :f
			};
			h.items.push(i);
			h.onAdd.dispatch(h, i)
		},
		getLength : function() {
			return c.get(this.id).options.length - 1
		},
		renderHTML : function() {
			var g, f = this;
			g = c.createHTML("option", {
				value :""
			}, "-- " + f.settings.title + " --");
			e(f.items, function(h) {
				g += c.createHTML("option", {
					value :h.value
				}, h.title)
			});
			g = c.createHTML("select", {
				id :f.id,
				"class" :"mceNativeListBox"
			}, g);
			return g
		},
		postRender : function() {
			var g = this, h;
			g.rendered = true;
			function f(j) {
				var i = g.items[j.target.selectedIndex - 1];
				if (i && (i = i.value)) {
					g.onChange.dispatch(g, i);
					if (g.settings.onselect) {
						g.settings.onselect(i)
					}
				}
			}
			b.add(g.id, "change", f);
			b.add(g.id, "keydown", function(j) {
				var i;
				b.remove(g.id, "change", h);
				i = b.add(g.id, "blur", function() {
					b.add(g.id, "change", f);
					b.remove(g.id, "blur", i)
				});
				if (j.keyCode == 13 || j.keyCode == 32) {
					f(j);
					return b.cancel(j)
				}
			});
			g.onPostRender.dispatch(g, c.get(g.id))
		}
	})
})(tinymce);
( function(c) {
	var b = c.DOM, a = c.dom.Event, d = c.each;
	c.create("tinymce.ui.MenuButton:tinymce.ui.Button", {
		MenuButton : function(f, e) {
			this.parent(f, e);
			this.onRenderMenu = new c.util.Dispatcher(this);
			e.menu_container = e.menu_container || b.doc.body
		},
		showMenu : function() {
			var g = this, j, i, h = b.get(g.id), f;
			if (g.isDisabled()) {
				return
			}
			if (!g.isMenuRendered) {
				g.renderMenu();
				g.isMenuRendered = true
			}
			if (g.isMenuVisible) {
				return g.hideMenu()
			}
			j = b.getPos(g.settings.menu_container);
			i = b.getPos(h);
			f = g.menu;
			f.settings.offset_x = i.x;
			f.settings.offset_y = i.y;
			f.settings.vp_offset_x = i.x;
			f.settings.vp_offset_y = i.y;
			f.settings.keyboard_focus = g._focused;
			f.showMenu(0, h.clientHeight);
			a.add(b.doc, "mousedown", g.hideMenu, g);
			g.setState("Selected", 1);
			g.isMenuVisible = 1
		},
		renderMenu : function() {
			var f = this, e;
			e = f.settings.control_manager.createDropMenu(f.id + "_menu", {
				menu_line :1,
				"class" :this.classPrefix + "Menu",
				icons :f.settings.icons
			});
			e.onHideMenu.add(f.hideMenu, f);
			f.onRenderMenu.dispatch(f, e);
			f.menu = e
		},
		hideMenu : function(g) {
			var f = this;
			if (g && g.type == "mousedown"
					&& b.getParent(g.target, function(h) {
						return h.id === f.id || h.id === f.id + "_open"
					})) {
				return
			}
			if (!g || !b.getParent(g.target, ".mceMenu")) {
				f.setState("Selected", 0);
				a.remove(b.doc, "mousedown", f.hideMenu, f);
				if (f.menu) {
					f.menu.hideMenu()
				}
			}
			f.isMenuVisible = 0
		},
		postRender : function() {
			var e = this, f = e.settings;
			a.add(e.id, "click", function() {
				if (!e.isDisabled()) {
					if (f.onclick) {
						f.onclick(e.value)
					}
					e.showMenu()
				}
			})
		}
	})
})(tinymce);
( function(c) {
	var b = c.DOM, a = c.dom.Event, d = c.each;
	c.create("tinymce.ui.SplitButton:tinymce.ui.MenuButton", {
		SplitButton : function(f, e) {
			this.parent(f, e);
			this.classPrefix = "mceSplitButton"
		},
		renderHTML : function() {
			var i, f = this, g = f.settings, e;
			i = "<tbody><tr>";
			if (g.image) {
				e = b.createHTML("img ", {
					src :g.image,
					"class" :"mceAction " + g["class"]
				})
			} else {
				e = b.createHTML("span", {
					"class" :"mceAction " + g["class"]
				}, "")
			}
			i += "<td>" + b.createHTML("a", {
				id :f.id + "_action",
				href :"javascript:;",
				"class" :"mceAction " + g["class"],
				onclick :"return false;",
				onmousedown :"return false;",
				title :g.title
			}, e) + "</td>";
			e = b.createHTML("span", {
				"class" :"mceOpen " + g["class"]
			});
			i += "<td>" + b.createHTML("a", {
				id :f.id + "_open",
				href :"javascript:;",
				"class" :"mceOpen " + g["class"],
				onclick :"return false;",
				onmousedown :"return false;",
				title :g.title
			}, e) + "</td>";
			i += "</tr></tbody>";
			return b.createHTML("table", {
				id :f.id,
				"class" :"mceSplitButton mceSplitButtonEnabled " + g["class"],
				cellpadding :"0",
				cellspacing :"0",
				onmousedown :"return false;",
				title :g.title
			}, i)
		},
		postRender : function() {
			var e = this, f = e.settings;
			if (f.onclick) {
				a.add(e.id + "_action", "click", function() {
					if (!e.isDisabled()) {
						f.onclick(e.value)
					}
				})
			}
			a.add(e.id + "_open", "click", e.showMenu, e);
			a.add(e.id + "_open", "focus", function() {
				e._focused = 1
			});
			a.add(e.id + "_open", "blur", function() {
				e._focused = 0
			});
			if (c.isIE6 || !b.boxModel) {
				a.add(e.id, "mouseover", function() {
					if (!b.hasClass(e.id, "mceSplitButtonDisabled")) {
						b.addClass(e.id, "mceSplitButtonHover")
					}
				});
				a.add(e.id, "mouseout", function() {
					if (!b.hasClass(e.id, "mceSplitButtonDisabled")) {
						b.removeClass(e.id, "mceSplitButtonHover")
					}
				})
			}
		},
		destroy : function() {
			this.parent();
			a.clear(this.id + "_action");
			a.clear(this.id + "_open")
		}
	})
})(tinymce);
( function(d) {
	var c = d.DOM, a = d.dom.Event, b = d.is, e = d.each;
	d
			.create(
					"tinymce.ui.ColorSplitButton:tinymce.ui.SplitButton",
					{
						ColorSplitButton : function(h, g) {
							var f = this;
							f.parent(h, g);
							f.settings = g = d
									.extend(
											{
												colors :"000000,993300,333300,003300,003366,000080,333399,333333,800000,FF6600,808000,008000,008080,0000FF,666699,808080,FF0000,FF9900,99CC00,339966,33CCCC,3366FF,800080,999999,FF00FF,FFCC00,FFFF00,00FF00,00FFFF,00CCFF,993366,C0C0C0,FF99CC,FFCC99,FFFF99,CCFFCC,CCFFFF,99CCFF,CC99FF,FFFFFF",
												grid_width :8,
												default_color :"#888888"
											}, f.settings);
							f.onShowMenu = new d.util.Dispatcher(f);
							f.onHideMenu = new d.util.Dispatcher(f);
							f.value = g.default_color
						},
						showMenu : function() {
							var f = this, g, j, i, h;
							if (f.isDisabled()) {
								return
							}
							if (!f.isMenuRendered) {
								f.renderMenu();
								f.isMenuRendered = true
							}
							if (f.isMenuVisible) {
								return f.hideMenu()
							}
							i = c.get(f.id);
							c.show(f.id + "_menu");
							c.addClass(i, "mceSplitButtonSelected");
							h = c.getPos(i);
							c.setStyles(f.id + "_menu", {
								left :h.x,
								top :h.y + i.clientHeight,
								zIndex :200000
							});
							i = 0;
							a.add(c.doc, "mousedown", f.hideMenu, f);
							if (f._focused) {
								f._keyHandler = a.add(f.id + "_menu",
										"keydown", function(k) {
											if (k.keyCode == 27) {
												f.hideMenu()
											}
										});
								c.select("a", f.id + "_menu")[0].focus()
							}
							f.onShowMenu.dispatch(f);
							f.isMenuVisible = 1
						},
						hideMenu : function(g) {
							var f = this;
							if (g && g.type == "mousedown"
									&& c.getParent(g.target, function(h) {
										return h.id === f.id + "_open"
									})) {
								return
							}
							if (!g
									|| !c.getParent(g.target,
											".mceSplitButtonMenu")) {
								c.removeClass(f.id, "mceSplitButtonSelected");
								a.remove(c.doc, "mousedown", f.hideMenu, f);
								a.remove(f.id + "_menu", "keydown",
										f._keyHandler);
								c.hide(f.id + "_menu")
							}
							f.onHideMenu.dispatch(f);
							f.isMenuVisible = 0
						},
						renderMenu : function() {
							var k = this, f, j = 0, l = k.settings, p, h, o, g;
							g = c.add(l.menu_container, "div", {
								id :k.id + "_menu",
								"class" :l.menu_class + " " + l["class"],
								style :"position:absolute;left:0;top:-1000px;"
							});
							f = c.add(g, "div", {
								"class" :l["class"] + " mceSplitButtonMenu"
							});
							c.add(f, "span", {
								"class" :"mceMenuLine"
							});
							p = c.add(f, "table", {
								"class" :"mceColorSplitMenu"
							});
							h = c.add(p, "tbody");
							j = 0;
							e(b(l.colors, "array") ? l.colors : l.colors
									.split(","), function(i) {
								i = i.replace(/^#/, "");
								if (!j--) {
									o = c.add(h, "tr");
									j = l.grid_width - 1
								}
								p = c.add(o, "td");
								p = c.add(p, "a", {
									href :"javascript:;",
									style : {
										backgroundColor :"#" + i
									},
									mce_color :"#" + i
								})
							});
							if (l.more_colors_func) {
								p = c.add(h, "tr");
								p = c.add(p, "td", {
									colspan :l.grid_width,
									"class" :"mceMoreColors"
								});
								p = c.add(p, "a", {
									id :k.id + "_more",
									href :"javascript:;",
									onclick :"return false;",
									"class" :"mceMoreColors"
								}, l.more_colors_title);
								a.add(p, "click", function(i) {
									l.more_colors_func.call(l.more_colors_scope
											|| this);
									return a.cancel(i)
								})
							}
							c.addClass(f, "mceColorSplitMenu");
							a.add(k.id + "_menu", "click", function(i) {
								var m;
								i = i.target;
								if (i.nodeName == "A"
										&& (m = i.getAttribute("mce_color"))) {
									k.setColor(m)
								}
								return a.cancel(i)
							});
							return g
						},
						setColor : function(g) {
							var f = this;
							c.setStyle(f.id + "_preview", "backgroundColor", g);
							f.value = g;
							f.hideMenu();
							f.settings.onselect(g)
						},
						postRender : function() {
							var f = this, g = f.id;
							f.parent();
							c.add(g + "_action", "div", {
								id :g + "_preview",
								"class" :"mceColorPreview"
							});
							c.setStyle(f.id + "_preview", "backgroundColor",
									f.value)
						},
						destroy : function() {
							this.parent();
							a.clear(this.id + "_menu");
							a.clear(this.id + "_more");
							c.remove(this.id + "_menu")
						}
					})
})(tinymce);
tinymce
		.create(
				"tinymce.ui.Toolbar:tinymce.ui.Container",
				{
					renderHTML : function() {
						var l = this, e = "", g, j, b = tinymce.DOM, m = l.settings, d, a, f, k;
						k = l.controls;
						for (d = 0; d < k.length; d++) {
							j = k[d];
							a = k[d - 1];
							f = k[d + 1];
							if (d === 0) {
								g = "mceToolbarStart";
								if (j.Button) {
									g += " mceToolbarStartButton"
								} else {
									if (j.SplitButton) {
										g += " mceToolbarStartSplitButton"
									} else {
										if (j.ListBox) {
											g += " mceToolbarStartListBox"
										}
									}
								}
								e += b.createHTML("td", {
									"class" :g
								}, b.createHTML("span", null, "<!-- IE -->"))
							}
							if (a && j.ListBox) {
								if (a.Button || a.SplitButton) {
									e += b.createHTML("td", {
										"class" :"mceToolbarEnd"
									}, b
											.createHTML("span", null,
													"<!-- IE -->"))
								}
							}
							if (b.stdMode) {
								e += '<td style="position: relative">'
										+ j.renderHTML() + "</td>"
							} else {
								e += "<td>" + j.renderHTML() + "</td>"
							}
							if (f && j.ListBox) {
								if (f.Button || f.SplitButton) {
									e += b.createHTML("td", {
										"class" :"mceToolbarStart"
									}, b
											.createHTML("span", null,
													"<!-- IE -->"))
								}
							}
						}
						g = "mceToolbarEnd";
						if (j.Button) {
							g += " mceToolbarEndButton"
						} else {
							if (j.SplitButton) {
								g += " mceToolbarEndSplitButton"
							} else {
								if (j.ListBox) {
									g += " mceToolbarEndListBox"
								}
							}
						}
						e += b.createHTML("td", {
							"class" :g
						}, b.createHTML("span", null, "<!-- IE -->"));
						return b.createHTML("table", {
							id :l.id,
							"class" :"mceToolbar"
									+ (m["class"] ? " " + m["class"] : ""),
							cellpadding :"0",
							cellspacing :"0",
							align :l.settings.align || ""
						}, "<tbody><tr>" + e + "</tr></tbody>")
					}
				});
( function(b) {
	var a = b.util.Dispatcher, c = b.each;
	b.create("tinymce.AddOnManager", {
		items : [],
		urls : {},
		lookup : {},
		onAdd :new a(this),
		get : function(d) {
			return this.lookup[d]
		},
		requireLangPack : function(f) {
			var d, e = b.EditorManager.settings;
			if (e && e.language) {
				d = this.urls[f] + "/langs/" + e.language + ".js";
				if (!b.dom.Event.domLoaded && !e.strict_mode) {
					b.ScriptLoader.load(d)
				} else {
					b.ScriptLoader.add(d)
				}
			}
		},
		add : function(e, d) {
			this.items.push(d);
			this.lookup[e] = d;
			this.onAdd.dispatch(this, e, d);
			return d
		},
		load : function(h, e, d, g) {
			var f = this;
			if (f.urls[h]) {
				return
			}
			if (e.indexOf("/") != 0 && e.indexOf("://") == -1) {
				e = b.baseURL + "/" + e
			}
			f.urls[h] = e.substring(0, e.lastIndexOf("/"));
			b.ScriptLoader.add(e, d, g)
		}
	});
	b.PluginManager = new b.AddOnManager();
	b.ThemeManager = new b.AddOnManager()
}(tinymce));
( function(f) {
	var g = f.each, h = f.extend, e = f.DOM, a = f.dom.Event, c = f.ThemeManager, b = f.PluginManager, d = f.explode;
	f
			.create(
					"static tinymce.EditorManager",
					{
						editors : {},
						i18n : {},
						activeEditor :null,
						preInit : function() {
							var i = this, j = window.location;
							f.documentBaseURL = j.href.replace(/[\?#].*$/, "")
									.replace(/[\/\\][^\/]+$/, "");
							if (!/[\/\\]$/.test(f.documentBaseURL)) {
								f.documentBaseURL += "/"
							}
							f.baseURL = new f.util.URI(f.documentBaseURL)
									.toAbsolute(f.baseURL);
							f.EditorManager.baseURI = new f.util.URI(f.baseURL);
							if (document.domain
									&& j.hostname != document.domain) {
								f.relaxedDomain = document.domain
							}
							i.onBeforeUnload = new f.util.Dispatcher(i);
							a.add(window, "beforeunload", function(k) {
								i.onBeforeUnload.dispatch(i, k)
							})
						},
						init : function(q) {
							var p = this, l, k = f.ScriptLoader, o, n, i = [], m;
							function j(u, v, r) {
								var t = u[v];
								if (!t) {
									return
								}
								if (f.is(t, "string")) {
									r = t.replace(/\.\w+$/, "");
									r = r ? f.resolve(r) : 0;
									t = f.resolve(t)
								}
								return t.apply(r || this, Array.prototype.slice
										.call(arguments, 2))
							}
							q = h(
									{
										theme :"simple",
										language :"en",
										strict_loading_mode :document.contentType == "application/xhtml+xml"
									}, q);
							p.settings = q;
							if (!a.domLoaded && !q.strict_loading_mode) {
								if (q.language) {
									k.add(f.baseURL + "/langs/" + q.language
											+ ".js")
								}
								if (q.theme && q.theme.charAt(0) != "-"
										&& !c.urls[q.theme]) {
									c.load(q.theme, "themes/" + q.theme
											+ "/editor_template" + f.suffix
											+ ".js")
								}
								if (q.plugins) {
									l = d(q.plugins);
									if (f.inArray(l, "compat2x") != -1) {
										b.load("compat2x",
												"plugins/compat2x/editor_plugin"
														+ f.suffix + ".js")
									}
									g(l, function(r) {
										if (r && r.charAt(0) != "-"
												&& !b.urls[r]) {
											if (!f.isWebKit && r == "safari") {
												return
											}
											b.load(r, "plugins/" + r
													+ "/editor_plugin"
													+ f.suffix + ".js")
										}
									})
								}
								k.loadQueue()
							}
							a.add(document, "init", function() {
								var r, t;
								j(q, "onpageload");
								if (q.browsers) {
									r = false;
									g(d(q.browsers), function(u) {
										switch (u) {
										case "ie":
										case "msie":
											if (f.isIE) {
												r = true
											}
											break;
										case "gecko":
											if (f.isGecko) {
												r = true
											}
											break;
										case "safari":
										case "webkit":
											if (f.isWebKit) {
												r = true
											}
											break;
										case "opera":
											if (f.isOpera) {
												r = true
											}
											break
										}
									});
									if (!r) {
										return
									}
								}
								switch (q.mode) {
								case "exact":
									r = q.elements || "";
									if (r.length > 0) {
										g(d(r), function(u) {
											if (e.get(u)) {
												m = new f.Editor(u, q);
												i.push(m);
												m.render(1)
											} else {
												o = 0;
												g(document.forms, function(v) {
													g(v.elements, function(w) {
														if (w.name === u) {
															u = "mce_editor_"
																	+ o;
															e.setAttrib(w,
																	"id", u);
															m = new f.Editor(u,
																	q);
															i.push(m);
															m.render(1)
														}
													})
												})
											}
										})
									}
									break;
								case "textareas":
								case "specific_textareas":
									function s(v, u) {
										return u.constructor === RegExp ? u
												.test(v.className) : e
												.hasClass(v, u)
									}
									g(e.select("textarea"), function(u) {
										if (q.editor_deselector
												&& s(u, q.editor_deselector)) {
											return
										}
										if (!q.editor_selector
												|| s(u, q.editor_selector)) {
											n = e.get(u.name);
											if (!u.id && !n) {
												u.id = u.name
											}
											if (!u.id || p.get(u.id)) {
												u.id = e.uniqueId()
											}
											m = new f.Editor(u.id, q);
											i.push(m);
											m.render(1)
										}
									});
									break
								}
								if (q.oninit) {
									r = t = 0;
									g(i, function(u) {
										t++;
										if (!u.initialized) {
											u.onInit.add( function() {
												r++;
												if (r == t) {
													j(q, "oninit")
												}
											})
										} else {
											r++
										}
										if (r == t) {
											j(q, "oninit")
										}
									})
								}
							})
						},
						get : function(i) {
							return this.editors[i]
						},
						getInstanceById : function(i) {
							return this.get(i)
						},
						add : function(i) {
							this.editors[i.id] = i;
							this._setActive(i);
							return i
						},
						remove : function(j) {
							var i = this;
							if (!i.editors[j.id]) {
								return null
							}
							delete i.editors[j.id];
							if (i.activeEditor == j) {
								g(i.editors, function(k) {
									i._setActive(k);
									return false
								})
							}
							j.destroy();
							return j
						},
						execCommand : function(o, m, l) {
							var n = this, k = n.get(l), i;
							switch (o) {
							case "mceFocus":
								k.focus();
								return true;
							case "mceAddEditor":
							case "mceAddControl":
								if (!n.get(l)) {
									new f.Editor(l, n.settings).render()
								}
								return true;
							case "mceAddFrameControl":
								i = l.window;
								i.tinyMCE = tinyMCE;
								i.tinymce = f;
								f.DOM.doc = i.document;
								f.DOM.win = i;
								k = new f.Editor(l.element_id, l);
								k.render();
								if (f.isIE) {
									function j() {
										k.destroy();
										i.detachEvent("onunload", j);
										i = i.tinyMCE = i.tinymce = null
									}
									i.attachEvent("onunload", j)
								}
								l.page_window = null;
								return true;
							case "mceRemoveEditor":
							case "mceRemoveControl":
								if (k) {
									k.remove()
								}
								return true;
							case "mceToggleEditor":
								if (!k) {
									n.execCommand("mceAddControl", 0, l);
									return true
								}
								if (k.isHidden()) {
									k.show()
								} else {
									k.hide()
								}
								return true
							}
							if (n.activeEditor) {
								return n.activeEditor.execCommand(o, m, l)
							}
							return false
						},
						execInstanceCommand : function(m, l, k, j) {
							var i = this.get(m);
							if (i) {
								return i.execCommand(l, k, j)
							}
							return false
						},
						triggerSave : function() {
							g(this.editors, function(i) {
								i.save()
							})
						},
						addI18n : function(k, l) {
							var i, j = this.i18n;
							if (!f.is(k, "string")) {
								g(k, function(n, m) {
									g(n, function(q, p) {
										g(q, function(s, r) {
											if (p === "common") {
												j[m + "." + r] = s
											} else {
												j[m + "." + p + "." + r] = s
											}
										})
									})
								})
							} else {
								g(l, function(n, m) {
									j[k + "." + m] = n
								})
							}
						},
						_setActive : function(i) {
							this.selectedInstance = this.activeEditor = i
						}
					});
	f.EditorManager.preInit()
})(tinymce);
var tinyMCE = window.tinyMCE = tinymce.EditorManager;
( function(n) {
	var o = n.DOM, k = n.dom.Event, f = n.extend, l = n.util.Dispatcher;
	var j = n.each, a = n.isGecko, b = n.isIE, e = n.isWebKit;
	var d = n.is, h = n.ThemeManager, c = n.PluginManager, i = n.EditorManager;
	var p = n.inArray, m = n.grep, g = n.explode;
	n
			.create(
					"tinymce.Editor",
					{
						Editor : function(u, r) {
							var q = this;
							q.id = q.editorId = u;
							q.execCommands = {};
							q.queryStateCommands = {};
							q.queryValueCommands = {};
							q.plugins = {};
							j( [ "onPreInit", "onBeforeRenderUI",
									"onPostRender", "onInit", "onRemove",
									"onActivate", "onDeactivate", "onClick",
									"onEvent", "onMouseUp", "onMouseDown",
									"onDblClick", "onKeyDown", "onKeyUp",
									"onKeyPress", "onContextMenu", "onSubmit",
									"onReset", "onPaste", "onPreProcess",
									"onPostProcess", "onBeforeSetContent",
									"onBeforeGetContent", "onSetContent",
									"onGetContent", "onLoadContent",
									"onSaveContent", "onNodeChange",
									"onChange", "onBeforeExecCommand",
									"onExecCommand", "onUndo", "onRedo",
									"onVisualAid", "onSetProgressState" ],
									function(s) {
										q[s] = new l(q)
									});
							q.settings = r = f(
									{
										id :u,
										language :"en",
										docs_language :"en",
										theme :"simple",
										skin :"default",
										delta_width :0,
										delta_height :0,
										popup_css :"",
										plugins :"",
										document_base_url :n.documentBaseURL,
										add_form_submit_trigger :1,
										submit_patch :1,
										add_unload_trigger :1,
										convert_urls :1,
										relative_urls :1,
										remove_script_host :1,
										table_inline_editing :0,
										object_resizing :1,
										cleanup :1,
										accessibility_focus :1,
										custom_shortcuts :1,
										custom_undo_redo_keyboard_shortcuts :1,
										custom_undo_redo_restore_selection :1,
										custom_undo_redo :1,
										doctype :'<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">',
										visual_table_class :"mceItemTable",
										visual :1,
										inline_styles :true,
										convert_fonts_to_spans :true,
										font_size_style_values :"xx-small,x-small,small,medium,large,x-large,xx-large",
										apply_source_formatting :1,
										directionality :"ltr",
										forced_root_block :"p",
										valid_elements :"@[id|class|style|title|dir<ltr?rtl|lang|xml::lang|onclick|ondblclick|onmousedown|onmouseup|onmouseover|onmousemove|onmouseout|onkeypress|onkeydown|onkeyup],a[rel|rev|charset|hreflang|tabindex|accesskey|type|name|href|target|title|class|onfocus|onblur],strong/b,em/i,strike,u,#p[align],-ol[type|compact],-ul[type|compact],-li,br,img[longdesc|usemap|src|border|alt=|title|hspace|vspace|width|height|align],-sub,-sup,-blockquote[cite],-table[border=0|cellspacing|cellpadding|width|frame|rules|height|align|summary|bgcolor|background|bordercolor],-tr[rowspan|width|height|align|valign|bgcolor|background|bordercolor],tbody,thead,tfoot,#td[colspan|rowspan|width|height|align|valign|bgcolor|background|bordercolor|scope],#th[colspan|rowspan|width|height|align|valign|scope],caption,-div,-span,-code,-pre,address,-h1,-h2,-h3,-h4,-h5,-h6,hr[size|noshade],-font[face|size|color],dd,dl,dt,cite,abbr,acronym,del[datetime|cite],ins[datetime|cite],object[classid|width|height|codebase|*],param[name|value],embed[type|width|height|src|*],script[src|type],map[name],area[shape|coords|href|alt|target],bdo,button,col[align|char|charoff|span|valign|width],colgroup[align|char|charoff|span|valign|width],dfn,fieldset,form[action|accept|accept-charset|enctype|method],input[accept|alt|checked|disabled|maxlength|name|readonly|size|src|type|value|tabindex|accesskey],kbd,label[for],legend,noscript,optgroup[label|disabled],option[disabled|label|selected|value],q[cite],samp,select[disabled|multiple|name|size],small,textarea[cols|rows|disabled|name|readonly],tt,var,big",
										hidden_input :1,
										padd_empty_editor :1,
										render_ui :1,
										init_theme :1,
										force_p_newlines :1,
										indentation :"30px",
										keep_styles :1,
										fix_table_elements :1,
										removeformat_selector :"span,b,strong,em,i,font,u,strike"
									}, r);
							q.documentBaseURI = new n.util.URI(
									r.document_base_url || n.documentBaseURL, {
										base_uri :tinyMCE.baseURI
									});
							q.baseURI = i.baseURI;
							q.execCallback("setup", q)
						},
						render : function(u) {
							var v = this, w = v.settings, x = v.id, q = n.ScriptLoader;
							if (!k.domLoaded) {
								k.add(document, "init", function() {
									v.render()
								});
								return
							}
							if (!u) {
								w.strict_loading_mode = 1;
								tinyMCE.settings = w
							}
							if (!v.getElement()) {
								return
							}
							if (w.strict_loading_mode) {
								q.settings.strict_mode = w.strict_loading_mode;
								n.DOM.settings.strict = 1
							}
							if (!/TEXTAREA|INPUT/i
									.test(v.getElement().nodeName)
									&& w.hidden_input && o.getParent(x, "form")) {
								o.insertAfter(o.create("input", {
									type :"hidden",
									name :x
								}), x)
							}
							if (n.WindowManager) {
								v.windowManager = new n.WindowManager(v)
							}
							if (w.encoding == "xml") {
								v.onGetContent.add( function(s, t) {
									if (t.save) {
										t.content = o.encode(t.content)
									}
								})
							}
							if (w.add_form_submit_trigger) {
								v.onSubmit.addToTop( function() {
									if (v.initialized) {
										v.save();
										v.isNotDirty = 1
									}
								})
							}
							if (w.add_unload_trigger) {
								v._beforeUnload = tinyMCE.onBeforeUnload
										.add( function() {
											if (v.initialized && !v.destroyed
													&& !v.isHidden()) {
												v.save( {
													format :"raw",
													no_events :true
												})
											}
										})
							}
							n.addUnload(v.destroy, v);
							if (w.submit_patch) {
								v.onBeforeRenderUI
										.add( function() {
											var s = v.getElement().form;
											if (!s) {
												return
											}
											if (s._mceOldSubmit) {
												return
											}
											if (!s.submit.nodeType
													&& !s.submit.length) {
												v.formElement = s;
												s._mceOldSubmit = s.submit;
												s.submit = function() {
													i.triggerSave();
													v.isNotDirty = 1;
													return this
															._mceOldSubmit(this)
												}
											}
											s = null
										})
							}
							function r() {
								if (w.language) {
									q.add(n.baseURL + "/langs/" + w.language
											+ ".js")
								}
								if (w.theme && w.theme.charAt(0) != "-"
										&& !h.urls[w.theme]) {
									h.load(w.theme, "themes/" + w.theme
											+ "/editor_template" + n.suffix
											+ ".js")
								}
								j(g(w.plugins), function(s) {
									if (s && s.charAt(0) != "-" && !c.urls[s]) {
										if (!e && s == "safari") {
											return
										}
										c.load(s, "plugins/" + s
												+ "/editor_plugin" + n.suffix
												+ ".js")
									}
								});
								q.loadQueue( function() {
									if (!v.removed) {
										v.init()
									}
								})
							}
							if (w.plugins.indexOf("compat2x") != -1) {
								c.load("compat2x",
										"plugins/compat2x/editor_plugin"
												+ n.suffix + ".js");
								q.loadQueue(r)
							} else {
								r()
							}
						},
						init : function() {
							var v, F = this, G = F.settings, C, z, B = F
									.getElement(), r, q, D, y, A, E;
							i.add(F);
							if (G.theme) {
								G.theme = G.theme.replace(/-/, "");
								r = h.get(G.theme);
								F.theme = new r();
								if (F.theme.init && G.init_theme) {
									F.theme.init(F, h.urls[G.theme]
											|| n.documentBaseURL.replace(/\/$/,
													""))
								}
							}
							j(g(G.plugins.replace(/\-/g, "")),
									function(w) {
										var H = c.get(w), t = c.urls[w]
												|| n.documentBaseURL.replace(
														/\/$/, ""), s;
										if (H) {
											s = new H(F, t);
											F.plugins[w] = s;
											if (s.init) {
												s.init(F, t)
											}
										}
									});
							if (G.popup_css !== false) {
								if (G.popup_css) {
									G.popup_css = F.documentBaseURI
											.toAbsolute(G.popup_css)
								} else {
									G.popup_css = F.baseURI
											.toAbsolute("themes/" + G.theme
													+ "/skins/" + G.skin
													+ "/dialog.css")
								}
							}
							if (G.popup_css_add) {
								G.popup_css += ","
										+ F.documentBaseURI
												.toAbsolute(G.popup_css_add)
							}
							F.controlManager = new n.ControlManager(F);
							F.undoManager = new n.UndoManager(F);
							F.undoManager.onAdd.add( function(t, s) {
								if (!s.initial) {
									return F.onChange.dispatch(F, s, t)
								}
							});
							F.undoManager.onUndo.add( function(t, s) {
								return F.onUndo.dispatch(F, s, t)
							});
							F.undoManager.onRedo.add( function(t, s) {
								return F.onRedo.dispatch(F, s, t)
							});
							if (G.custom_undo_redo) {
								F.onExecCommand.add( function(t, w, u, H, s) {
									if (w != "Undo" && w != "Redo"
											&& w != "mceRepaint"
											&& (!s || !s.skip_undo)) {
										F.undoManager.add()
									}
								})
							}
							F.onExecCommand.add( function(s, t) {
								if (!/^(FontName|FontSize)$/.test(t)) {
									F.nodeChanged()
								}
							});
							if (a) {
								function x(s, t) {
									if (!t || !t.initial) {
										F.execCommand("mceRepaint")
									}
								}
								F.onUndo.add(x);
								F.onRedo.add(x);
								F.onSetContent.add(x)
							}
							F.onBeforeRenderUI.dispatch(F, F.controlManager);
							if (G.render_ui) {
								C = G.width || B.style.width || B.offsetWidth;
								z = G.height || B.style.height
										|| B.offsetHeight;
								F.orgDisplay = B.style.display;
								E = /^[0-9\.]+(|px)$/i;
								if (E.test("" + C)) {
									C = Math.max(parseInt(C)
											+ (r.deltaWidth || 0), 100)
								}
								if (E.test("" + z)) {
									z = Math.max(parseInt(z)
											+ (r.deltaHeight || 0), 100)
								}
								r = F.theme.renderUI( {
									targetNode :B,
									width :C,
									height :z,
									deltaWidth :G.delta_width,
									deltaHeight :G.delta_height
								});
								F.editorContainer = r.editorContainer
							}
							o.setStyles(r.sizeContainer || r.editorContainer, {
								width :C,
								height :z
							});
							z = (r.iframeHeight || z)
									+ (typeof (z) == "number" ? (r.deltaHeight || 0)
											: "");
							if (z < 100) {
								z = 100
							}
							F.iframeHTML = G.doctype
									+ '<html><head xmlns="http://www.w3.org/1999/xhtml"><base href="'
									+ F.documentBaseURI.getURI() + '" />';
							F.iframeHTML += '<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />';
							if (n.relaxedDomain) {
								F.iframeHTML += '<script type="text/javascript">document.domain = "' + n.relaxedDomain + '";<\/script>'
							}
							y = G.body_id || "tinymce";
							if (y.indexOf("=") != -1) {
								y = F.getParam("body_id", "", "hash");
								y = y[F.id] || y
							}
							A = G.body_class || "";
							if (A.indexOf("=") != -1) {
								A = F.getParam("body_class", "", "hash");
								A = A[F.id] || ""
							}
							F.iframeHTML += '</head><body id="' + y
									+ '" class="mceContentBody ' + A
									+ '"></body></html>';
							if (n.relaxedDomain) {
								if (b
										|| (n.isOpera && parseFloat(opera
												.version()) >= 9.5)) {
									D = 'javascript:(function(){document.open();document.domain="'
											+ document.domain
											+ '";var ed = window.parent.tinyMCE.get("'
											+ F.id
											+ '");document.write(ed.iframeHTML);document.close();ed.setupIframe();})()'
								} else {
									if (n.isOpera) {
										D = 'javascript:(function(){document.open();document.domain="' + document.domain + '";document.close();ed.setupIframe();})()'
									}
								}
							}
							v = o.add(r.iframeContainer, "iframe", {
								id :F.id + "_ifr",
								src :D || 'javascript:""',
								frameBorder :"0",
								style : {
									width :"100%",
									height :z
								}
							});
							F.contentAreaContainer = r.iframeContainer;
							o.get(r.editorContainer).style.display = F.orgDisplay;
							o.get(F.id).style.display = "none";
							if (!b || !n.relaxedDomain) {
								F.setupIframe()
							}
							B = v = r = null
						},
						setupIframe : function() {
							var z = this, A = z.settings, u = o.get(z.id), v = z
									.getDoc(), r, x;
							if (!b || !n.relaxedDomain) {
								v.open();
								v.write(z.iframeHTML);
								v.close()
							}
							if (!b) {
								try {
									if (!A.readonly) {
										v.designMode = "On"
									}
								} catch (w) {
								}
							}
							if (b) {
								x = z.getBody();
								o.hide(x);
								if (!A.readonly) {
									x.contentEditable = true
								}
								o.show(x)
							}
							z.dom = new n.DOM.DOMUtils(z.getDoc(), {
								keep_values :true,
								url_converter :z.convertURL,
								url_converter_scope :z,
								hex_colors :A.force_hex_style_colors,
								class_filter :A.class_filter,
								update_styles :1,
								fix_ie_paragraphs :1
							});
							z.serializer = new n.dom.Serializer(
									{
										entity_encoding :A.entity_encoding,
										entities :A.entities,
										valid_elements :A.verify_html === false ? "*[*]"
												: A.valid_elements,
										extended_valid_elements :A.extended_valid_elements,
										valid_child_elements :A.valid_child_elements,
										invalid_elements :A.invalid_elements,
										fix_table_elements :A.fix_table_elements,
										fix_list_elements :A.fix_list_elements,
										fix_content_duplication :A.fix_content_duplication,
										convert_fonts_to_spans :A.convert_fonts_to_spans,
										font_size_classes :A.font_size_classes,
										font_size_style_values :A.font_size_style_values,
										apply_source_formatting :A.apply_source_formatting,
										remove_linebreaks :A.remove_linebreaks,
										element_format :A.element_format,
										dom :z.dom
									});
							z.selection = new n.dom.Selection(z.dom,
									z.getWin(), z.serializer);
							z.forceBlocks = new n.ForceBlocks(z, {
								forced_root_block :A.forced_root_block
							});
							z.editorCommands = new n.EditorCommands(z);
							z.serializer.onPreProcess.add( function(s, t) {
								return z.onPreProcess.dispatch(z, t, s)
							});
							z.serializer.onPostProcess.add( function(s, t) {
								return z.onPostProcess.dispatch(z, t, s)
							});
							z.onPreInit.dispatch(z);
							if (!A.gecko_spellcheck) {
								z.getBody().spellcheck = 0
							}
							if (!A.readonly) {
								z._addEvents()
							}
							z.controlManager.onPostRender.dispatch(z,
									z.controlManager);
							z.onPostRender.dispatch(z);
							if (A.directionality) {
								z.getBody().dir = A.directionality
							}
							if (A.nowrap) {
								z.getBody().style.whiteSpace = "nowrap"
							}
							if (A.auto_resize) {
								z.onNodeChange.add(z.resizeToContent, z)
							}
							if (A.custom_elements) {
								function y(s, t) {
									j(g(A.custom_elements), function(B) {
										var C;
										if (B.indexOf("~") === 0) {
											B = B.substring(1);
											C = "span"
										} else {
											C = "div"
										}
										t.content = t.content.replace(
												new RegExp("<(" + B
														+ ")([^>]*)>", "g"),
												"<" + C + ' mce_name="$1"$2>');
										t.content = t.content.replace(
												new RegExp("</(" + B + ")>",
														"g"), "</" + C + ">")
									})
								}
								z.onBeforeSetContent.add(y);
								z.onPostProcess.add( function(s, t) {
									if (t.set) {
										y(s, t)
									}
								})
							}
							if (A.handle_node_change_callback) {
								z.onNodeChange.add( function(t, s, B) {
									z.execCallback(
											"handle_node_change_callback",
											z.id, B, -1, -1, true, z.selection
													.isCollapsed())
								})
							}
							if (A.save_callback) {
								z.onSaveContent.add( function(s, B) {
									var t = z.execCallback("save_callback",
											z.id, B.content, z.getBody());
									if (t) {
										B.content = t
									}
								})
							}
							if (A.onchange_callback) {
								z.onChange.add( function(t, s) {
									z.execCallback("onchange_callback", z, s)
								})
							}
							if (A.convert_newlines_to_brs) {
								z.onBeforeSetContent.add( function(s, t) {
									if (t.initial) {
										t.content = t.content.replace(/\r?\n/g,
												"<br />")
									}
								})
							}
							if (A.fix_nesting && b) {
								z.onBeforeSetContent.add( function(s, t) {
									t.content = z._fixNesting(t.content)
								})
							}
							if (A.preformatted) {
								z.onPostProcess
										.add( function(s, t) {
											t.content = t.content.replace(
													/^\s*<pre.*?>/, "");
											t.content = t.content.replace(
													/<\/pre>\s*$/, "");
											if (t.set) {
												t.content = '<pre class="mceItemHidden">'
														+ t.content + "</pre>"
											}
										})
							}
							if (A.verify_css_classes) {
								z.serializer.attribValueFilter = function(D, B) {
									var C, t;
									if (D == "class") {
										if (!z.classesRE) {
											t = z.dom.getClasses();
											if (t.length > 0) {
												C = "";
												j(t, function(s) {
													C += (C ? "|" : "")
															+ s["class"]
												});
												z.classesRE = new RegExp("("
														+ C + ")", "gi")
											}
										}
										return !z.classesRE
												|| /(\bmceItem\w+\b|\bmceTemp\w+\b)/g
														.test(B)
												|| z.classesRE.test(B) ? B : ""
									}
									return B
								}
							}
							if (A.convert_fonts_to_spans) {
								z._convertFonts()
							}
							if (A.inline_styles) {
								z._convertInlineElements()
							}
							if (A.cleanup_callback) {
								z.onBeforeSetContent.add( function(s, t) {
									t.content = z.execCallback(
											"cleanup_callback",
											"insert_to_editor", t.content, t)
								});
								z.onPreProcess.add( function(s, t) {
									if (t.set) {
										z.execCallback("cleanup_callback",
												"insert_to_editor_dom", t.node,
												t)
									}
									if (t.get) {
										z.execCallback("cleanup_callback",
												"get_from_editor_dom", t.node,
												t)
									}
								});
								z.onPostProcess.add( function(s, t) {
									if (t.set) {
										t.content = z.execCallback(
												"cleanup_callback",
												"insert_to_editor", t.content,
												t)
									}
									if (t.get) {
										t.content = z
												.execCallback(
														"cleanup_callback",
														"get_from_editor",
														t.content, t)
									}
								})
							}
							if (A.save_callback) {
								z.onGetContent.add( function(s, t) {
									if (t.save) {
										t.content = z.execCallback(
												"save_callback", z.id,
												t.content, z.getBody())
									}
								})
							}
							if (A.handle_event_callback) {
								z.onEvent.add( function(s, t, B) {
									if (z.execCallback("handle_event_callback",
											t, s, B) === false) {
										k.cancel(t)
									}
								})
							}
							z.onSetContent.add( function() {
								z.addVisual(z.getBody())
							});
							if (A.padd_empty_editor) {
								z.onPostProcess
										.add( function(s, t) {
											t.content = t.content
													.replace(
															/^(<p[^>]*>(&nbsp;|&#160;|\s|\u00a0|)<\/p>[\r\n]*|<br \/>[\r\n]*)$/,
															"")
										})
							}
							if (a) {
								function q(s, t) {
									j(s.dom.select("a"), function(C) {
										var B = C.parentNode;
										if (s.dom.isBlock(B)
												&& B.lastChild === C) {
											s.dom.add(B, "br", {
												mce_bogus :1
											})
										}
									})
								}
								z.onExecCommand.add( function(s, t) {
									if (t === "CreateLink") {
										q(s)
									}
								});
								z.onSetContent.add(z.selection.onSetContent
										.add(q))
							}
							if (a && !A.readonly) {
								try {
									v.designMode = "Off";
									v.designMode = "On"
								} catch (w) {
								}
							}
							setTimeout( function() {
								if (z.removed) {
									return
								}
								z.load( {
									initial :true,
									format :(A.cleanup_on_startup ? "html"
											: "raw")
								});
								z.startContent = z.getContent( {
									format :"raw"
								});
								z.undoManager.add( {
									initial :true
								});
								z.initialized = true;
								z.onInit.dispatch(z);
								z.execCallback("setupcontent_callback", z.id, z
										.getBody(), z.getDoc());
								z.execCallback("init_instance_callback", z);
								z.focus(true);
								z.nodeChanged( {
									initial :1
								});
								if (A.content_css) {
									n.each(g(A.content_css), function(s) {
										z.dom.loadCSS(z.documentBaseURI
												.toAbsolute(s))
									})
								}
								if (A.auto_focus) {
									setTimeout( function() {
										var s = i.get(A.auto_focus);
										s.selection.select(s.getBody(), 1);
										s.selection.collapse(1);
										s.getWin().focus()
									}, 100)
								}
							}, 1);
							u = null
						},
						focus : function(r) {
							var u, q = this, s = q.settings.content_editable;
							if (!r) {
								if (!s
										&& (!b || q.selection.getNode().ownerDocument != q
												.getDoc())) {
									q.getWin().focus()
								}
							}
							if (i.activeEditor != q) {
								if ((u = i.activeEditor) != null) {
									u.onDeactivate.dispatch(u, q)
								}
								q.onActivate.dispatch(q, u)
							}
							i._setActive(q)
						},
						execCallback : function(v) {
							var q = this, u = q.settings[v], r;
							if (!u) {
								return
							}
							if (q.callbackLookup && (r = q.callbackLookup[v])) {
								u = r.func;
								r = r.scope
							}
							if (d(u, "string")) {
								r = u.replace(/\.\w+$/, "");
								r = r ? n.resolve(r) : 0;
								u = n.resolve(u);
								q.callbackLookup = q.callbackLookup || {};
								q.callbackLookup[v] = {
									func :u,
									scope :r
								}
							}
							return u.apply(r || q, Array.prototype.slice.call(
									arguments, 1))
						},
						translate : function(q) {
							var t = this.settings.language || "en", r = i.i18n;
							if (!q) {
								return ""
							}
							return r[t + "." + q]
									|| q.replace(/{\#([^}]+)\}/g,
											function(u, s) {
												return r[t + "." + s] || "{#"
														+ s + "}"
											})
						},
						getLang : function(r, q) {
							return i.i18n[(this.settings.language || "en")
									+ "." + r]
									|| (d(q) ? q : "{#" + r + "}")
						},
						getParam : function(w, s, q) {
							var t = n.trim, r = d(this.settings[w]) ? this.settings[w]
									: s, u;
							if (q === "hash") {
								u = {};
								if (d(r, "string")) {
									j(r.indexOf("=") > 0 ? r
											.split(/[;,](?![^=;,]*(?:[;,]|$))/)
											: r.split(","), function(x) {
										x = x.split("=");
										if (x.length > 1) {
											u[t(x[0])] = t(x[1])
										} else {
											u[t(x[0])] = t(x)
										}
									})
								} else {
									u = r
								}
								return u
							}
							return r
						},
						nodeChanged : function(u) {
							var q = this, r = q.selection, v = r.getNode()
									|| q.getBody();
							if (q.initialized) {
								q.onNodeChange.dispatch(q, u ? u.controlManager
										|| q.controlManager : q.controlManager,
										b && v.ownerDocument != q.getDoc() ? q
												.getBody() : v,
										r.isCollapsed(), u)
							}
						},
						addButton : function(u, r) {
							var q = this;
							q.buttons = q.buttons || {};
							q.buttons[u] = r
						},
						addCommand : function(t, r, q) {
							this.execCommands[t] = {
								func :r,
								scope :q || this
							}
						},
						addQueryStateHandler : function(t, r, q) {
							this.queryStateCommands[t] = {
								func :r,
								scope :q || this
							}
						},
						addQueryValueHandler : function(t, r, q) {
							this.queryValueCommands[t] = {
								func :r,
								scope :q || this
							}
						},
						addShortcut : function(s, v, q, u) {
							var r = this, w;
							if (!r.settings.custom_shortcuts) {
								return false
							}
							r.shortcuts = r.shortcuts || {};
							if (d(q, "string")) {
								w = q;
								q = function() {
									r.execCommand(w, false, null)
								}
							}
							if (d(q, "object")) {
								w = q;
								q = function() {
									r.execCommand(w[0], w[1], w[2])
								}
							}
							j(g(s), function(t) {
								var x = {
									func :q,
									scope :u || this,
									desc :v,
									alt :false,
									ctrl :false,
									shift :false
								};
								j(g(t, "+"), function(y) {
									switch (y) {
									case "alt":
									case "ctrl":
									case "shift":
										x[y] = true;
										break;
									default:
										x.charCode = y.charCodeAt(0);
										x.keyCode = y.toUpperCase().charCodeAt(
												0)
									}
								});
								r.shortcuts[(x.ctrl ? "ctrl" : "") + ","
										+ (x.alt ? "alt" : "") + ","
										+ (x.shift ? "shift" : "") + ","
										+ x.keyCode] = x
							});
							return true
						},
						execCommand : function(x, w, z, q) {
							var u = this, v = 0, y, r;
							if (!/^(mceAddUndoLevel|mceEndUndoLevel|mceBeginUndoLevel|mceRepaint|SelectAll)$/
									.test(x)
									&& (!q || !q.skip_focus)) {
								u.focus()
							}
							y = {};
							u.onBeforeExecCommand.dispatch(u, x, w, z, y);
							if (y.terminate) {
								return false
							}
							if (u.execCallback("execcommand_callback", u.id,
									u.selection.getNode(), x, w, z)) {
								u.onExecCommand.dispatch(u, x, w, z, q);
								return true
							}
							if (y = u.execCommands[x]) {
								r = y.func.call(y.scope, w, z);
								if (r !== true) {
									u.onExecCommand.dispatch(u, x, w, z, q);
									return r
								}
							}
							j(u.plugins, function(s) {
								if (s.execCommand && s.execCommand(x, w, z)) {
									u.onExecCommand.dispatch(u, x, w, z, q);
									v = 1;
									return false
								}
							});
							if (v) {
								return true
							}
							if (u.theme && u.theme.execCommand
									&& u.theme.execCommand(x, w, z)) {
								u.onExecCommand.dispatch(u, x, w, z, q);
								return true
							}
							if (n.GlobalCommands.execCommand(u, x, w, z)) {
								u.onExecCommand.dispatch(u, x, w, z, q);
								return true
							}
							if (u.editorCommands.execCommand(x, w, z)) {
								u.onExecCommand.dispatch(u, x, w, z, q);
								return true
							}
							u.getDoc().execCommand(x, w, z);
							u.onExecCommand.dispatch(u, x, w, z, q)
						},
						queryCommandState : function(w) {
							var r = this, v, u;
							if (r._isHidden()) {
								return
							}
							if (v = r.queryStateCommands[w]) {
								u = v.func.call(v.scope);
								if (u !== true) {
									return u
								}
							}
							v = r.editorCommands.queryCommandState(w);
							if (v !== -1) {
								return v
							}
							try {
								return this.getDoc().queryCommandState(w)
							} catch (q) {
							}
						},
						queryCommandValue : function(w) {
							var r = this, v, u;
							if (r._isHidden()) {
								return
							}
							if (v = r.queryValueCommands[w]) {
								u = v.func.call(v.scope);
								if (u !== true) {
									return u
								}
							}
							v = r.editorCommands.queryCommandValue(w);
							if (d(v)) {
								return v
							}
							try {
								return this.getDoc().queryCommandValue(w)
							} catch (q) {
							}
						},
						show : function() {
							var q = this;
							o.show(q.getContainer());
							o.hide(q.id);
							q.load()
						},
						hide : function() {
							var q = this, r = q.getDoc();
							if (b && r) {
								r.execCommand("SelectAll")
							}
							q.save();
							o.hide(q.getContainer());
							o.setStyle(q.id, "display", q.orgDisplay)
						},
						isHidden : function() {
							return !o.isHidden(this.id)
						},
						setProgressState : function(q, r, s) {
							this.onSetProgressState.dispatch(this, q, r, s);
							return q
						},
						resizeToContent : function() {
							var q = this;
							o.setStyle(q.id + "_ifr", "height",
									q.getBody().scrollHeight)
						},
						load : function(u) {
							var q = this, s = q.getElement(), r;
							if (s) {
								u = u || {};
								u.load = true;
								r = q.setContent(d(s.value) ? s.value
										: s.innerHTML, u);
								u.element = s;
								if (!u.no_events) {
									q.onLoadContent.dispatch(q, u)
								}
								u.element = s = null;
								return r
							}
						},
						save : function(v) {
							var q = this, u = q.getElement(), r, s;
							if (!u || !q.initialized) {
								return
							}
							v = v || {};
							v.save = true;
							if (!v.no_events) {
								q.undoManager.typing = 0;
								q.undoManager.add()
							}
							v.element = u;
							r = v.content = q.getContent(v);
							if (!v.no_events) {
								q.onSaveContent.dispatch(q, v)
							}
							r = v.content;
							if (!/TEXTAREA|INPUT/i.test(u.nodeName)) {
								u.innerHTML = r;
								if (s = o.getParent(q.id, "form")) {
									j(s.elements, function(t) {
										if (t.name == q.id) {
											t.value = r;
											return false
										}
									})
								}
							} else {
								u.value = r
							}
							v.element = u = null;
							return r
						},
						setContent : function(r, s) {
							var q = this;
							s = s || {};
							s.format = s.format || "html";
							s.set = true;
							s.content = r;
							if (!s.no_events) {
								q.onBeforeSetContent.dispatch(q, s)
							}
							if (!n.isIE && (r.length === 0 || /^\s+$/.test(r))) {
								s.content = q.dom.setHTML(q.getBody(),
										'<br mce_bogus="1" />');
								s.format = "raw"
							}
							s.content = q.dom.setHTML(q.getBody(), n
									.trim(s.content));
							if (s.format != "raw" && q.settings.cleanup) {
								s.getInner = true;
								s.content = q.dom.setHTML(q.getBody(),
										q.serializer.serialize(q.getBody(), s))
							}
							if (!s.no_events) {
								q.onSetContent.dispatch(q, s)
							}
							return s.content
						},
						getContent : function(s) {
							var q = this, r;
							s = s || {};
							s.format = s.format || "html";
							s.get = true;
							if (!s.no_events) {
								q.onBeforeGetContent.dispatch(q, s)
							}
							if (s.format != "raw" && q.settings.cleanup) {
								s.getInner = true;
								r = q.serializer.serialize(q.getBody(), s)
							} else {
								r = q.getBody().innerHTML
							}
							r = r.replace(/^\s*|\s*$/g, "");
							s.content = r;
							if (!s.no_events) {
								q.onGetContent.dispatch(q, s)
							}
							return s.content
						},
						isDirty : function() {
							var q = this;
							return n.trim(q.startContent) != n.trim(q
									.getContent( {
										format :"raw",
										no_events :1
									}))
									&& !q.isNotDirty
						},
						getContainer : function() {
							var q = this;
							if (!q.container) {
								q.container = o.get(q.editorContainer || q.id
										+ "_parent")
							}
							return q.container
						},
						getContentAreaContainer : function() {
							return this.contentAreaContainer
						},
						getElement : function() {
							return o.get(this.settings.content_element
									|| this.id)
						},
						getWin : function() {
							var q = this, r;
							if (!q.contentWindow) {
								r = o.get(q.id + "_ifr");
								if (r) {
									q.contentWindow = r.contentWindow
								}
							}
							return q.contentWindow
						},
						getDoc : function() {
							var r = this, q;
							if (!r.contentDocument) {
								q = r.getWin();
								if (q) {
									r.contentDocument = q.document
								}
							}
							return r.contentDocument
						},
						getBody : function() {
							return this.bodyElement || this.getDoc().body
						},
						convertURL : function(q, x, w) {
							var r = this, v = r.settings;
							if (v.urlconverter_callback) {
								return r.execCallback("urlconverter_callback",
										q, w, true, x)
							}
							if (!v.convert_urls || (w && w.nodeName == "LINK")
									|| q.indexOf("file:") === 0) {
								return q
							}
							if (v.relative_urls) {
								return r.documentBaseURI.toRelative(q)
							}
							q = r.documentBaseURI.toAbsolute(q,
									v.remove_script_host);
							return q
						},
						addVisual : function(u) {
							var q = this, r = q.settings;
							u = u || q.getBody();
							if (!d(q.hasVisual)) {
								q.hasVisual = r.visual
							}
							j(q.dom.select("table,a", u), function(t) {
								var s;
								switch (t.nodeName) {
								case "TABLE":
									s = q.dom.getAttrib(t, "border");
									if (!s || s == "0") {
										if (q.hasVisual) {
											q.dom.addClass(t,
													r.visual_table_class)
										} else {
											q.dom.removeClass(t,
													r.visual_table_class)
										}
									}
									return;
								case "A":
									s = q.dom.getAttrib(t, "name");
									if (s) {
										if (q.hasVisual) {
											q.dom.addClass(t, "mceItemAnchor")
										} else {
											q.dom.removeClass(t,
													"mceItemAnchor")
										}
									}
									return
								}
							});
							q.onVisualAid.dispatch(q, u, q.hasVisual)
						},
						remove : function() {
							var q = this, r = q.getContainer();
							q.removed = 1;
							q.hide();
							q.execCallback("remove_instance_callback", q);
							q.onRemove.dispatch(q);
							q.onExecCommand.listeners = [];
							i.remove(q);
							o.remove(r)
						},
						destroy : function(r) {
							var q = this;
							if (q.destroyed) {
								return
							}
							if (!r) {
								n.removeUnload(q.destroy);
								tinyMCE.onBeforeUnload.remove(q._beforeUnload);
								if (q.theme && q.theme.destroy) {
									q.theme.destroy()
								}
								q.controlManager.destroy();
								q.selection.destroy();
								q.dom.destroy();
								if (!q.settings.content_editable) {
									k.clear(q.getWin());
									k.clear(q.getDoc())
								}
								k.clear(q.getBody());
								k.clear(q.formElement)
							}
							if (q.formElement) {
								q.formElement.submit = q.formElement._mceOldSubmit;
								q.formElement._mceOldSubmit = null
							}
							q.contentAreaContainer = q.formElement = q.container = q.settings.content_element = q.bodyElement = q.contentDocument = q.contentWindow = null;
							if (q.selection) {
								q.selection = q.selection.win = q.selection.dom = q.selection.dom.doc = null
							}
							q.destroyed = 1
						},
						_addEvents : function() {
							var w = this, v, y = w.settings, x = {
								mouseup :"onMouseUp",
								mousedown :"onMouseDown",
								click :"onClick",
								keyup :"onKeyUp",
								keydown :"onKeyDown",
								keypress :"onKeyPress",
								submit :"onSubmit",
								reset :"onReset",
								contextmenu :"onContextMenu",
								dblclick :"onDblClick",
								paste :"onPaste"
							};
							function u(t, A) {
								var s = t.type;
								if (w.removed) {
									return
								}
								if (w.onEvent.dispatch(w, t, A) !== false) {
									w[x[t.fakeType || t.type]]
											.dispatch(w, t, A)
								}
							}
							j(
									x,
									function(t, s) {
										switch (s) {
										case "contextmenu":
											if (n.isOpera) {
												k
														.add(
																w.getBody(),
																"mousedown",
																function(A) {
																	if (A.ctrlKey) {
																		A.fakeType = "contextmenu";
																		u(A)
																	}
																})
											} else {
												k.add(w.getBody(), s, u)
											}
											break;
										case "paste":
											k
													.add(
															w.getBody(),
															s,
															function(E) {
																var A, C, B, D;
																if (E.clipboardData) {
																	A = E.clipboardData
																			.getData("text/plain")
																} else {
																	if (n.isIE) {
																		A = w
																				.getWin().clipboardData
																				.getData("Text")
																	}
																}
																u(E, {
																	text :A,
																	html :C
																})
															});
											break;
										case "submit":
										case "reset":
											k.add(w.getElement().form
													|| o
															.getParent(w.id,
																	"form"), s,
													u);
											break;
										default:
											k.add(y.content_editable ? w
													.getBody() : w.getDoc(), s,
													u)
										}
									});
							k.add(y.content_editable ? w.getBody() : (a ? w
									.getDoc() : w.getWin()), "focus", function(
									s) {
								w.focus(true)
							});
							if (n.isGecko) {
								k
										.add(
												w.getDoc(),
												"DOMNodeInserted",
												function(t) {
													var s;
													t = t.target;
													if (t.nodeType === 1
															&& t.nodeName === "IMG"
															&& (s = t
																	.getAttribute("mce_src"))) {
														t.src = w.documentBaseURI
																.toAbsolute(s)
													}
												})
							}
							if (a) {
								function q() {
									var B = this, D = B.getDoc(), C = B.settings;
									if (a && !C.readonly) {
										if (B._isHidden()) {
											try {
												if (!C.content_editable) {
													D.designMode = "On"
												}
											} catch (A) {
											}
										}
										try {
											D.execCommand("styleWithCSS", 0,
													false)
										} catch (A) {
											if (!B._isHidden()) {
												try {
													D.execCommand("useCSS", 0,
															true)
												} catch (A) {
												}
											}
										}
										if (!C.table_inline_editing) {
											try {
												D
														.execCommand(
																"enableInlineTableEditing",
																false, false)
											} catch (A) {
											}
										}
										if (!C.object_resizing) {
											try {
												D.execCommand(
														"enableObjectResizing",
														false, false)
											} catch (A) {
											}
										}
									}
								}
								w.onBeforeExecCommand.add(q);
								w.onMouseDown.add(q)
							}
							w.onMouseUp.add(w.nodeChanged);
							w.onClick.add(w.nodeChanged);
							w.onKeyUp.add( function(s, t) {
								var A = t.keyCode;
								if ((A >= 33 && A <= 36)
										|| (A >= 37 && A <= 40) || A == 13
										|| A == 45 || A == 46 || A == 8
										|| (n.isMac && (A == 91 || A == 93))
										|| t.ctrlKey) {
									w.nodeChanged()
								}
							});
							w.onReset.add( function() {
								w.setContent(w.startContent, {
									format :"raw"
								})
							});
							if (y.custom_shortcuts) {
								if (y.custom_undo_redo_keyboard_shortcuts) {
									w.addShortcut("ctrl+z", w
											.getLang("undo_desc"), "Undo");
									w.addShortcut("ctrl+y", w
											.getLang("redo_desc"), "Redo")
								}
								if (a) {
									w.addShortcut("ctrl+b", w
											.getLang("bold_desc"), "Bold");
									w.addShortcut("ctrl+i", w
											.getLang("italic_desc"), "Italic");
									w.addShortcut("ctrl+u", w
											.getLang("underline_desc"),
											"Underline")
								}
								for (v = 1; v <= 6; v++) {
									w.addShortcut("ctrl+" + v, "", [
											"FormatBlock", false,
											"<h" + v + ">" ])
								}
								w.addShortcut("ctrl+7", "", [ "FormatBlock",
										false, "<p>" ]);
								w.addShortcut("ctrl+8", "", [ "FormatBlock",
										false, "<div>" ]);
								w.addShortcut("ctrl+9", "", [ "FormatBlock",
										false, "<address>" ]);
								function z(t) {
									var s = null;
									if (!t.altKey && !t.ctrlKey && !t.metaKey) {
										return s
									}
									j(
											w.shortcuts,
											function(A) {
												if (n.isMac
														&& A.ctrl != t.metaKey) {
													return
												} else {
													if (!n.isMac
															&& A.ctrl != t.ctrlKey) {
														return
													}
												}
												if (A.alt != t.altKey) {
													return
												}
												if (A.shift != t.shiftKey) {
													return
												}
												if (t.keyCode == A.keyCode
														|| (t.charCode && t.charCode == A.charCode)) {
													s = A;
													return false
												}
											});
									return s
								}
								w.onKeyUp.add( function(s, t) {
									var A = z(t);
									if (A) {
										return k.cancel(t)
									}
								});
								w.onKeyPress.add( function(s, t) {
									var A = z(t);
									if (A) {
										return k.cancel(t)
									}
								});
								w.onKeyDown.add( function(s, t) {
									var A = z(t);
									if (A) {
										A.func.call(A.scope);
										return k.cancel(t)
									}
								})
							}
							if (n.isIE) {
								k
										.add(
												w.getDoc(),
												"controlselect",
												function(A) {
													var t = w.resizeInfo, s;
													A = A.target;
													if (A.nodeName !== "IMG") {
														return
													}
													if (t) {
														k.remove(t.node, t.ev,
																t.cb)
													}
													if (!w.dom.hasClass(A,
															"mceItemNoResize")) {
														ev = "resizeend";
														s = k
																.add(
																		A,
																		ev,
																		function(
																				C) {
																			var B;
																			C = C.target;
																			if (B = w.dom
																					.getStyle(
																							C,
																							"width")) {
																				w.dom
																						.setAttrib(
																								C,
																								"width",
																								B
																										.replace(
																												/[^0-9%]+/g,
																												""));
																				w.dom
																						.setStyle(
																								C,
																								"width",
																								"")
																			}
																			if (B = w.dom
																					.getStyle(
																							C,
																							"height")) {
																				w.dom
																						.setAttrib(
																								C,
																								"height",
																								B
																										.replace(
																												/[^0-9%]+/g,
																												""));
																				w.dom
																						.setStyle(
																								C,
																								"height",
																								"")
																			}
																		})
													} else {
														ev = "resizestart";
														s = k.add(A,
																"resizestart",
																k.cancel, k)
													}
													t = w.resizeInfo = {
														node :A,
														ev :ev,
														cb :s
													}
												});
								w.onKeyDown.add( function(s, t) {
									switch (t.keyCode) {
									case 8:
										if (w.selection.getRng().item) {
											w.selection.getRng().item(0)
													.removeNode();
											return k.cancel(t)
										}
									}
								})
							}
							if (n.isOpera) {
								w.onClick.add( function(s, t) {
									k.prevent(t)
								})
							}
							if (y.custom_undo_redo) {
								function r() {
									w.undoManager.typing = 0;
									w.undoManager.add()
								}
								if (n.isIE) {
									k.add(w.getWin(), "blur", function(s) {
										var t;
										if (w.selection) {
											t = w.selection.getNode();
											if (!w.removed
													&& t.ownerDocument
													&& t.ownerDocument != w
															.getDoc()) {
												r()
											}
										}
									})
								} else {
									k.add(w.getDoc(), "blur", function() {
										if (w.selection && !w.removed) {
											r()
										}
									})
								}
								w.onMouseDown.add(r);
								w.onKeyUp
										.add( function(s, t) {
											if ((t.keyCode >= 33 && t.keyCode <= 36)
													|| (t.keyCode >= 37 && t.keyCode <= 40)
													|| t.keyCode == 13
													|| t.keyCode == 45
													|| t.ctrlKey) {
												w.undoManager.typing = 0;
												w.undoManager.add()
											}
										});
								w.onKeyDown
										.add( function(s, t) {
											if ((t.keyCode >= 33 && t.keyCode <= 36)
													|| (t.keyCode >= 37 && t.keyCode <= 40)
													|| t.keyCode == 13
													|| t.keyCode == 45) {
												if (w.undoManager.typing) {
													w.undoManager.add();
													w.undoManager.typing = 0
												}
												return
											}
											if (!w.undoManager.typing) {
												w.undoManager.add();
												w.undoManager.typing = 1
											}
										})
							}
						},
						_convertInlineElements : function() {
							var z = this, B = z.settings, r = z.dom, y, w, u, A, q;
							function x(s, t) {
								if (!B.inline_styles) {
									return
								}
								if (t.get) {
									j(
											z.dom.select("table,u,strike",
													t.node),
											function(v) {
												switch (v.nodeName) {
												case "TABLE":
													if (y = r.getAttrib(v,
															"height")) {
														r.setStyle(v, "height",
																y);
														r.setAttrib(v,
																"height", "")
													}
													break;
												case "U":
												case "STRIKE":
													v.style.textDecoration = v.nodeName == "U" ? "underline"
															: "line-through";
													r.setAttrib(v, "mce_style",
															"");
													r.setAttrib(v, "mce_name",
															"span");
													break
												}
											})
								} else {
									if (t.set) {
										j(
												z.dom.select("table,span",
														t.node).reverse(),
												function(v) {
													if (v.nodeName == "TABLE") {
														if (y = r.getStyle(v,
																"height")) {
															r
																	.setAttrib(
																			v,
																			"height",
																			y
																					.replace(
																							/[^0-9%]+/g,
																							""))
														}
													} else {
														if (v.style.textDecoration == "underline") {
															u = "u"
														} else {
															if (v.style.textDecoration == "line-through") {
																u = "strike"
															} else {
																u = ""
															}
														}
														if (u) {
															v.style.textDecoration = "";
															r
																	.setAttrib(
																			v,
																			"mce_style",
																			"");
															w = r
																	.create(
																			u,
																			{
																				style :r
																						.getAttrib(
																								v,
																								"style")
																			});
															r.replace(w, v, 1)
														}
													}
												})
									}
								}
							}
							z.onPreProcess.add(x);
							if (!B.cleanup_on_startup) {
								z.onSetContent.add( function(s, t) {
									if (t.initial) {
										x(z, {
											node :z.getBody(),
											set :1
										})
									}
								})
							}
						},
						_convertFonts : function() {
							var w = this, x = w.settings, z = w.dom, v, r, q, u;
							if (!x.inline_styles) {
								return
							}
							v = [ 8, 10, 12, 14, 18, 24, 36 ];
							r = [ "xx-small", "x-small", "small", "medium",
									"large", "x-large", "xx-large" ];
							if (q = x.font_size_style_values) {
								q = g(q)
							}
							if (u = x.font_size_classes) {
								u = g(u)
							}
							function y(B) {
								var C, A, t, s;
								if (!x.inline_styles) {
									return
								}
								t = w.dom.select("font", B);
								for (s = t.length - 1; s >= 0; s--) {
									C = t[s];
									A = z.create("span", {
										style :z.getAttrib(C, "style"),
										"class" :z.getAttrib(C, "class")
									});
									z
											.setStyles(
													A,
													{
														fontFamily :z
																.getAttrib(C,
																		"face"),
														color :z.getAttrib(C,
																"color"),
														backgroundColor :C.style.backgroundColor
													});
									if (C.size) {
										if (q) {
											z.setStyle(A, "fontSize",
													q[parseInt(C.size) - 1])
										} else {
											z.setAttrib(A, "class",
													u[parseInt(C.size) - 1])
										}
									}
									z.setAttrib(A, "mce_style", "");
									z.replace(A, C, 1)
								}
							}
							w.onPreProcess.add( function(s, t) {
								if (t.get) {
									y(t.node)
								}
							});
							w.onSetContent.add( function(s, t) {
								if (t.initial) {
									y(t.node)
								}
							})
						},
						_isHidden : function() {
							var q;
							if (!a) {
								return 0
							}
							q = this.selection.getSel();
							return (!q || !q.rangeCount || q.rangeCount == 0)
						},
						_fixNesting : function(r) {
							var t = [], q;
							r = r.replace(/<(\/)?([^\s>]+)[^>]*?>/g, function(
									u, s, w) {
								var v;
								if (s === "/") {
									if (!t.length) {
										return ""
									}
									if (w !== t[t.length - 1].tag) {
										for (q = t.length - 1; q >= 0; q--) {
											if (t[q].tag === w) {
												t[q].close = 1;
												break
											}
										}
										return ""
									} else {
										t.pop();
										if (t.length && t[t.length - 1].close) {
											u = u + "</" + t[t.length - 1].tag
													+ ">";
											t.pop()
										}
									}
								} else {
									if (/^(br|hr|input|meta|img|link|param)$/i
											.test(w)) {
										return u
									}
									if (/\/>$/.test(u)) {
										return u
									}
									t.push( {
										tag :w
									})
								}
								return u
							});
							for (q = t.length - 1; q >= 0; q--) {
								r += "</" + t[q].tag + ">"
							}
							return r
						}
					})
})(tinymce);
( function(d) {
	var f = d.each, c = d.isIE, a = d.isGecko, b = d.isOpera, e = d.isWebKit;
	d
			.create(
					"tinymce.EditorCommands",
					{
						EditorCommands : function(g) {
							this.editor = g
						},
						execCommand : function(k, j, l) {
							var h = this, g = h.editor, i;
							switch (k) {
							case "mceResetDesignMode":
							case "mceBeginUndoLevel":
								return true;
							case "unlink":
								h.UnLink();
								return true;
							case "JustifyLeft":
							case "JustifyCenter":
							case "JustifyRight":
							case "JustifyFull":
								h.mceJustify(k, k.substring(7).toLowerCase());
								return true;
							default:
								i = this[k];
								if (i) {
									i.call(this, j, l);
									return true
								}
							}
							return false
						},
						Indent : function() {
							var g = this.editor, l = g.dom, j = g.selection, k, h, i;
							h = g.settings.indentation;
							i = /[a-z%]+$/i.exec(h);
							h = parseInt(h);
							if (g.settings.inline_styles
									&& (!this.queryStateInsertUnorderedList() && !this
											.queryStateInsertOrderedList())) {
								f(
										j.getSelectedBlocks(),
										function(m) {
											l
													.setStyle(
															m,
															"paddingLeft",
															(parseInt(m.style.paddingLeft || 0) + h)
																	+ i)
										});
								return
							}
							g.getDoc().execCommand("Indent", false, null);
							if (c) {
								l.getParent(j.getNode(), function(m) {
									if (m.nodeName == "BLOCKQUOTE") {
										m.dir = m.style.cssText = ""
									}
								})
							}
						},
						Outdent : function() {
							var h = this.editor, m = h.dom, k = h.selection, l, g, i, j;
							i = h.settings.indentation;
							j = /[a-z%]+$/i.exec(i);
							i = parseInt(i);
							if (h.settings.inline_styles
									&& (!this.queryStateInsertUnorderedList() && !this
											.queryStateInsertOrderedList())) {
								f(k.getSelectedBlocks(), function(n) {
									g = Math.max(0,
											parseInt(n.style.paddingLeft || 0)
													- i);
									m
											.setStyle(n, "paddingLeft", g ? g
													+ j : "")
								});
								return
							}
							h.getDoc().execCommand("Outdent", false, null)
						},
						mceSetContent : function(h, g) {
							this.editor.setContent(g)
						},
						mceToggleVisualAid : function() {
							var g = this.editor;
							g.hasVisual = !g.hasVisual;
							g.addVisual()
						},
						mceReplaceContent : function(h, g) {
							var i = this.editor.selection;
							i.setContent(g.replace(/\{\$selection\}/g, i
									.getContent( {
										format :"text"
									})))
						},
						mceInsertLink : function(i, h) {
							var g = this.editor, j = g.selection, k = g.dom
									.getParent(j.getNode(), "A");
							if (d.is(h, "string")) {
								h = {
									href :h
								}
							}
							function l(m) {
								f(h, function(o, n) {
									g.dom.setAttrib(m, n, o)
								})
							}
							if (!k) {
								g.execCommand("CreateLink", false,
										"javascript:mctmp(0);");
								f(g.dom.select("a[href=javascript:mctmp(0);]"),
										function(m) {
											l(m)
										})
							} else {
								if (h.href) {
									l(k)
								} else {
									g.dom.remove(k, 1)
								}
							}
						},
						UnLink : function() {
							var g = this.editor, h = g.selection;
							if (h.isCollapsed()) {
								h.select(h.getNode())
							}
							g.getDoc().execCommand("unlink", false, null);
							h.collapse(0)
						},
						FontName : function(i, h) {
							var j = this, g = j.editor, k = g.selection, l;
							if (!h) {
								if (k.isCollapsed()) {
									k.select(k.getNode())
								}
							} else {
								if (g.settings.convert_fonts_to_spans) {
									j._applyInlineStyle("span", {
										style : {
											fontFamily :h
										}
									})
								} else {
									g.getDoc()
											.execCommand("FontName", false, h)
								}
							}
						},
						FontSize : function(j, i) {
							var h = this.editor, l = h.settings, k, g;
							if (l.convert_fonts_to_spans && i >= 1 && i <= 7) {
								g = d.explode(l.font_size_style_values);
								k = d.explode(l.font_size_classes);
								if (k) {
									i = k[i - 1] || i
								} else {
									i = g[i - 1] || i
								}
							}
							if (i >= 1 && i <= 7) {
								h.getDoc().execCommand("FontSize", false, i)
							} else {
								this._applyInlineStyle("span", {
									style : {
										fontSize :i
									}
								})
							}
						},
						queryCommandValue : function(h) {
							var g = this["queryValue" + h];
							if (g) {
								return g.call(this, h)
							}
							return false
						},
						queryCommandState : function(h) {
							var g;
							switch (h) {
							case "JustifyLeft":
							case "JustifyCenter":
							case "JustifyRight":
							case "JustifyFull":
								return this.queryStateJustify(h, h.substring(7)
										.toLowerCase());
							default:
								if (g = this["queryState" + h]) {
									return g.call(this, h)
								}
							}
							return -1
						},
						_queryState : function(h) {
							try {
								return this.editor.getDoc()
										.queryCommandState(h)
							} catch (g) {
							}
						},
						_queryVal : function(h) {
							try {
								return this.editor.getDoc()
										.queryCommandValue(h)
							} catch (g) {
							}
						},
						queryValueFontSize : function() {
							var h = this.editor, g = 0, i;
							if (i = h.dom.getParent(h.selection.getNode(),
									"SPAN")) {
								g = i.style.fontSize
							}
							if (!g && (b || e)) {
								if (i = h.dom.getParent(h.selection.getNode(),
										"FONT")) {
									g = i.size
								}
								return g
							}
							return g || this._queryVal("FontSize")
						},
						queryValueFontName : function() {
							var h = this.editor, g = 0, i;
							if (i = h.dom.getParent(h.selection.getNode(),
									"FONT")) {
								g = i.face
							}
							if (i = h.dom.getParent(h.selection.getNode(),
									"SPAN")) {
								g = i.style.fontFamily.replace(/, /g, ",")
										.replace(/[\'\"]/g, "").toLowerCase()
							}
							if (!g) {
								g = this._queryVal("FontName")
							}
							return g
						},
						mceJustify : function(o, p) {
							var k = this.editor, m = k.selection, g = m
									.getNode(), q = g.nodeName, h, j, i = k.dom, l;
							if (k.settings.inline_styles
									&& this.queryStateJustify(o, p)) {
								l = 1
							}
							h = i.getParent(g, k.dom.isBlock);
							if (q == "IMG") {
								if (p == "full") {
									return
								}
								if (l) {
									if (p == "center") {
										i.setStyle(h || g.parentNode,
												"textAlign", "")
									}
									i.setStyle(g, "float", "");
									this.mceRepaint();
									return
								}
								if (p == "center") {
									if (h && /^(TD|TH)$/.test(h.nodeName)) {
										h = 0
									}
									if (!h || h.childNodes.length > 1) {
										j = i.create("p");
										j.appendChild(g.cloneNode(false));
										if (h) {
											i.insertAfter(j, h)
										} else {
											i.insertAfter(j, g)
										}
										i.remove(g);
										g = j.firstChild;
										h = j
									}
									i.setStyle(h, "textAlign", p);
									i.setStyle(g, "float", "")
								} else {
									i.setStyle(g, "float", p);
									i.setStyle(h || g.parentNode, "textAlign",
											"")
								}
								this.mceRepaint();
								return
							}
							if (k.settings.inline_styles
									&& k.settings.forced_root_block) {
								if (l) {
									p = ""
								}
								f(m.getSelectedBlocks(i.getParent(m.getStart(),
										i.isBlock), i.getParent(m.getEnd(),
										i.isBlock)), function(n) {
									i.setAttrib(n, "align", "");
									i.setStyle(n, "textAlign",
											p == "full" ? "justify" : p)
								});
								return
							} else {
								if (!l) {
									k.getDoc().execCommand(o, false, null)
								}
							}
							if (k.settings.inline_styles) {
								if (l) {
									i.getParent(k.selection.getNode(),
											function(r) {
												if (r.style
														&& r.style.textAlign) {
													i.setStyle(r, "textAlign",
															"")
												}
											});
									return
								}
								f(i.select("*"), function(s) {
									var r = s.align;
									if (r) {
										if (r == "full") {
											r = "justify"
										}
										i.setStyle(s, "textAlign", r);
										i.setAttrib(s, "align", "")
									}
								})
							}
						},
						mceSetCSSClass : function(h, g) {
							this.mceSetStyleInfo(0, {
								command :"setattrib",
								name :"class",
								value :g
							})
						},
						getSelectedElement : function() {
							var w = this, o = w.editor, n = o.dom, s = o.selection, h = s
									.getRng(), l, k, u, p, j, g, q, i, x, v;
							if (s.isCollapsed() || h.item) {
								return s.getNode()
							}
							v = o.settings.merge_styles_invalid_parents;
							if (d.is(v, "string")) {
								v = new RegExp(v, "i")
							}
							if (c) {
								l = h.duplicate();
								l.collapse(true);
								u = l.parentElement();
								k = h.duplicate();
								k.collapse(false);
								p = k.parentElement();
								if (u != p) {
									l.move("character", 1);
									u = l.parentElement()
								}
								if (u == p) {
									l = h.duplicate();
									l.moveToElementText(u);
									if (l.compareEndPoints("StartToStart", h) == 0
											&& l
													.compareEndPoints(
															"EndToEnd", h) == 0) {
										return v && v.test(u.nodeName) ? null
												: u
									}
								}
							} else {
								function m(r) {
									return n.getParent(r, "*")
								}
								u = h.startContainer;
								p = h.endContainer;
								j = h.startOffset;
								g = h.endOffset;
								if (!h.collapsed) {
									if (u == p) {
										if (j - g < 2) {
											if (u.hasChildNodes()) {
												i = u.childNodes[j];
												return v && v.test(i.nodeName) ? null
														: i
											}
										}
									}
								}
								if (u.nodeType != 3 || p.nodeType != 3) {
									return null
								}
								if (j == 0) {
									i = m(u);
									if (i && i.firstChild != u) {
										i = null
									}
								}
								if (j == u.nodeValue.length) {
									q = u.nextSibling;
									if (q && q.nodeType == 1) {
										i = u.nextSibling
									}
								}
								if (g == 0) {
									q = p.previousSibling;
									if (q && q.nodeType == 1) {
										x = q
									}
								}
								if (g == p.nodeValue.length) {
									x = m(p);
									if (x && x.lastChild != p) {
										x = null
									}
								}
								if (i == x) {
									return v && i && v.test(i.nodeName) ? null
											: i
								}
							}
							return null
						},
						mceSetStyleInfo : function(n, m) {
							var q = this, h = q.editor, j = h.getDoc(), g = h.dom, i, k, r = h.selection, p = m.wrapper
									|| "span", k = r.getBookmark(), o;
							function l(t, s) {
								if (t.nodeType == 1) {
									switch (m.command) {
									case "setattrib":
										return g.setAttrib(t, m.name, m.value);
									case "setstyle":
										return g.setStyle(t, m.name, m.value);
									case "removeformat":
										return g.setAttrib(t, "class", "")
									}
								}
							}
							o = h.settings.merge_styles_invalid_parents;
							if (d.is(o, "string")) {
								o = new RegExp(o, "i")
							}
							if ((i = q.getSelectedElement())
									&& !h.settings.force_span_wrappers) {
								l(i, 1)
							} else {
								j.execCommand("FontName", false, "__");
								f(g.select("span,font"), function(u) {
									var s, t;
									if (g.getAttrib(u, "face") == "__"
											|| u.style.fontFamily === "__") {
										s = g.create(p, {
											mce_new :"1"
										});
										l(s);
										f(u.childNodes, function(v) {
											s.appendChild(v.cloneNode(true))
										});
										g.replace(s, u)
									}
								})
							}
							f(g.select(p).reverse(), function(t) {
								var s = t.parentNode;
								if (!g.getAttrib(t, "mce_new")) {
									s = g.getParent(t, "*[mce_new]");
									if (s) {
										g.remove(t, 1)
									}
								}
							});
							f(g.select(p).reverse(), function(t) {
								var s = t.parentNode;
								if (!s || !g.getAttrib(t, "mce_new")) {
									return
								}
								if (h.settings.force_span_wrappers
										&& s.nodeName != "SPAN") {
									return
								}
								if (s.nodeName == p.toUpperCase()
										&& s.childNodes.length == 1) {
									return g.remove(s, 1)
								}
								if (t.nodeType == 1
										&& (!o || !o.test(s.nodeName))
										&& s.childNodes.length == 1) {
									l(s);
									g.setAttrib(t, "class", "")
								}
							});
							f(
									g.select(p).reverse(),
									function(s) {
										if (g.getAttrib(s, "mce_new")
												|| (g.getAttribs(s).length <= 1 && s.className === "")) {
											if (!g.getAttrib(s, "class")
													&& !g.getAttrib(s, "style")) {
												return g.remove(s, 1)
											}
											g.setAttrib(s, "mce_new", "")
										}
									});
							r.moveToBookmark(k)
						},
						queryStateJustify : function(k, h) {
							var g = this.editor, j = g.selection.getNode(), i = g.dom;
							if (j && j.nodeName == "IMG") {
								if (i.getStyle(j, "float") == h) {
									return 1
								}
								return j.parentNode.style.textAlign == h
							}
							j = i.getParent(g.selection.getStart(),
									function(l) {
										return l.nodeType == 1
												&& l.style.textAlign
									});
							if (h == "full") {
								h = "justify"
							}
							if (g.settings.inline_styles) {
								return (j && j.style.textAlign == h)
							}
							return this._queryState(k)
						},
						ForeColor : function(i, h) {
							var g = this.editor;
							if (g.settings.convert_fonts_to_spans) {
								this._applyInlineStyle("span", {
									style : {
										color :h
									}
								});
								return
							} else {
								g.getDoc().execCommand("ForeColor", false, h)
							}
						},
						HiliteColor : function(i, k) {
							var h = this, g = h.editor, j = g.getDoc();
							if (g.settings.convert_fonts_to_spans) {
								this._applyInlineStyle("span", {
									style : {
										backgroundColor :k
									}
								});
								return
							}
							function l(n) {
								if (!a) {
									return
								}
								try {
									j.execCommand("styleWithCSS", 0, n)
								} catch (m) {
									j.execCommand("useCSS", 0, !n)
								}
							}
							if (a || b) {
								l(true);
								j.execCommand("hilitecolor", false, k);
								l(false)
							} else {
								j.execCommand("BackColor", false, k)
							}
						},
						FormatBlock : function(n, h) {
							var o = this, l = o.editor, p = l.selection, j = l.dom, g, k, m;
							function i(q) {
								return /^(P|DIV|H[1-6]|ADDRESS|BLOCKQUOTE|PRE)$/
										.test(q.nodeName)
							}
							g = j.getParent(p.getNode(), function(q) {
								return i(q)
							});
							if (g) {
								if ((c && i(g.parentNode))
										|| g.nodeName == "DIV") {
									k = l.dom.create(h);
									f(j.getAttribs(g), function(q) {
										j.setAttrib(k, q.nodeName, j.getAttrib(
												g, q.nodeName))
									});
									m = p.getBookmark();
									j.replace(k, g, 1);
									p.moveToBookmark(m);
									l.nodeChanged();
									return
								}
							}
							h = l.settings.forced_root_block ? (h || "<p>") : h;
							if (h.indexOf("<") == -1) {
								h = "<" + h + ">"
							}
							if (d.isGecko) {
								h = h
										.replace(
												/<(div|blockquote|code|dt|dd|dl|samp)>/gi,
												"$1")
							}
							l.getDoc().execCommand("FormatBlock", false, h)
						},
						mceCleanup : function() {
							var h = this.editor, i = h.selection, g = i
									.getBookmark();
							h.setContent(h.getContent());
							i.moveToBookmark(g)
						},
						mceRemoveNode : function(j, k) {
							var h = this.editor, i = h.selection, g, l = k
									|| i.getNode();
							if (l == h.getBody()) {
								return
							}
							g = i.getBookmark();
							h.dom.remove(l, 1);
							i.moveToBookmark(g);
							h.nodeChanged()
						},
						mceSelectNodeDepth : function(i, j) {
							var g = this.editor, h = g.selection, k = 0;
							g.dom.getParent(h.getNode(), function(l) {
								if (l.nodeType == 1 && k++ == j) {
									h.select(l);
									g.nodeChanged();
									return false
								}
							}, g.getBody())
						},
						mceSelectNode : function(h, g) {
							this.editor.selection.select(g)
						},
						mceInsertContent : function(g, h) {
							this.editor.selection.setContent(h)
						},
						mceInsertRawHTML : function(h, i) {
							var g = this.editor;
							g.selection.setContent("tiny_mce_marker");
							g.setContent(g.getContent().replace(
									/tiny_mce_marker/g, i))
						},
						mceRepaint : function() {
							var i, g, j = this.editor;
							if (d.isGecko) {
								try {
									i = j.selection;
									g = i.getBookmark(true);
									if (i.getSel()) {
										i.getSel().selectAllChildren(
												j.getBody())
									}
									i.collapse(true);
									i.moveToBookmark(g)
								} catch (h) {
								}
							}
						},
						queryStateUnderline : function() {
							var g = this.editor, h = g.selection.getNode();
							if (h && h.nodeName == "A") {
								return false
							}
							return this._queryState("Underline")
						},
						queryStateOutdent : function() {
							var g = this.editor, h;
							if (g.settings.inline_styles) {
								if ((h = g.dom.getParent(
										g.selection.getStart(), g.dom.isBlock))
										&& parseInt(h.style.paddingLeft) > 0) {
									return true
								}
								if ((h = g.dom.getParent(g.selection.getEnd(),
										g.dom.isBlock))
										&& parseInt(h.style.paddingLeft) > 0) {
									return true
								}
							}
							return this.queryStateInsertUnorderedList()
									|| this.queryStateInsertOrderedList()
									|| (!g.settings.inline_styles && !!g.dom
											.getParent(g.selection.getNode(),
													"BLOCKQUOTE"))
						},
						queryStateInsertUnorderedList : function() {
							return this.editor.dom.getParent(
									this.editor.selection.getNode(), "UL")
						},
						queryStateInsertOrderedList : function() {
							return this.editor.dom.getParent(
									this.editor.selection.getNode(), "OL")
						},
						queryStatemceBlockQuote : function() {
							return !!this.editor.dom.getParent(
									this.editor.selection.getStart(), function(
											g) {
										return g.nodeName === "BLOCKQUOTE"
									})
						},
						_applyInlineStyle : function(o, j, m) {
							var q = this, n = q.editor, l = n.dom, i, p = {}, k, r;
							o = o.toUpperCase();
							if (m && m.check_classes && j["class"]) {
								m.check_classes.push(j["class"])
							}
							function h() {
								f(
										l.select(o).reverse(),
										function(t) {
											var s = 0;
											f(
													l.getAttribs(t),
													function(u) {
														if (u.nodeName
																.substring(0, 1) != "_"
																&& l
																		.getAttrib(
																				t,
																				u.nodeName) != "") {
															s++
														}
													});
											if (s == 0) {
												l.remove(t, 1)
											}
										})
							}
							function g() {
								var s;
								f(l.select("span,font"), function(t) {
									if (t.style.fontFamily == "mceinline"
											|| t.face == "mceinline") {
										if (!s) {
											s = n.selection.getBookmark()
										}
										j._mce_new = "1";
										l.replace(l.create(o, j), t, 1)
									}
								});
								f(
										l.select(o + "[_mce_new]"),
										function(u) {
											function t(v) {
												if (v.nodeType == 1) {
													f(j.style, function(x, w) {
														l.setStyle(v, w, "")
													});
													if (j["class"]
															&& v.className && m) {
														f(
																m.check_classes,
																function(w) {
																	if (l
																			.hasClass(
																					v,
																					w)) {
																		l
																				.removeClass(
																						v,
																						w)
																	}
																})
													}
												}
											}
											f(l.select(o, u), t);
											if (u.parentNode
													&& u.parentNode.nodeType == 1
													&& u.parentNode.childNodes.length == 1) {
												t(u.parentNode)
											}
											l
													.getParent(
															u.parentNode,
															function(v) {
																if (v.nodeType == 1) {
																	if (j.style) {
																		f(
																				j.style,
																				function(
																						y,
																						x) {
																					var w;
																					if (!p[x]
																							&& (w = l
																									.getStyle(
																											v,
																											x))) {
																						if (w === y) {
																							l
																									.setStyle(
																											u,
																											x,
																											"")
																						}
																						p[x] = 1
																					}
																				})
																	}
																	if (j["class"]
																			&& v.className
																			&& m) {
																		f(
																				m.check_classes,
																				function(
																						w) {
																					if (l
																							.hasClass(
																									v,
																									w)) {
																						l
																								.removeClass(
																										u,
																										w)
																					}
																				})
																	}
																}
																return false
															});
											u.removeAttribute("_mce_new")
										});
								h();
								n.selection.moveToBookmark(s);
								return !!s
							}
							n.focus();
							n.getDoc().execCommand("FontName", false,
									"mceinline");
							g();
							if (k = q._applyInlineStyle.keyhandler) {
								n.onKeyUp.remove(k);
								n.onKeyPress.remove(k);
								n.onKeyDown.remove(k);
								n.onSetContent
										.remove(q._applyInlineStyle.chandler)
							}
							if (n.selection.isCollapsed()) {
								if (!c) {
									f(l.getParents(n.selection.getNode(),
											"span"), function(s) {
										f(j.style, function(u, t) {
											var w;
											if (w = l.getStyle(s, t)) {
												if (w == u) {
													l.setStyle(s, t, "");
													r = 2;
													return false
												}
												r = 1;
												return false
											}
										});
										if (r) {
											return false
										}
									});
									if (r == 2) {
										i = n.selection.getBookmark();
										h();
										n.selection.moveToBookmark(i);
										window.setTimeout( function() {
											n.nodeChanged()
										}, 1);
										return
									}
								}
								q._pendingStyles = d.extend(q._pendingStyles
										|| {}, j.style);
								q._applyInlineStyle.chandler = n.onSetContent
										.add( function() {
											delete q._pendingStyles
										});
								q._applyInlineStyle.keyhandler = k = function(s) {
									if (q._pendingStyles) {
										j.style = q._pendingStyles;
										delete q._pendingStyles
									}
									if (g()) {
										n.onKeyDown
												.remove(q._applyInlineStyle.keyhandler);
										n.onKeyPress
												.remove(q._applyInlineStyle.keyhandler)
									}
									if (s.type == "keyup") {
										n.onKeyUp
												.remove(q._applyInlineStyle.keyhandler)
									}
								};
								n.onKeyDown.add(k);
								n.onKeyPress.add(k);
								n.onKeyUp.add(k)
							} else {
								q._pendingStyles = 0
							}
						}
					})
})(tinymce);
( function(a) {
	a.create("tinymce.UndoManager", {
		index :0,
		data :null,
		typing :0,
		UndoManager : function(c) {
			var d = this, b = a.util.Dispatcher;
			d.editor = c;
			d.data = [];
			d.onAdd = new b(this);
			d.onUndo = new b(this);
			d.onRedo = new b(this)
		},
		add : function(d) {
			var g = this, f, e = g.editor, c, h = e.settings, j;
			d = d || {};
			d.content = d.content || e.getContent( {
				format :"raw",
				no_events :1
			});
			d.content = d.content.replace(/^\s*|\s*$/g, "");
			j = g.data[g.index > 0
					&& (g.index == 0 || g.index == g.data.length) ? g.index - 1
					: g.index];
			if (!d.initial && j && d.content == j.content) {
				return null
			}
			if (h.custom_undo_redo_levels) {
				if (g.data.length > h.custom_undo_redo_levels) {
					for (f = 0; f < g.data.length - 1; f++) {
						g.data[f] = g.data[f + 1]
					}
					g.data.length--;
					g.index = g.data.length
				}
			}
			if (h.custom_undo_redo_restore_selection && !d.initial) {
				d.bookmark = c = d.bookmark || e.selection.getBookmark()
			}
			if (g.index < g.data.length) {
				g.index++
			}
			if (g.data.length === 0 && !d.initial) {
				return null
			}
			g.data.length = g.index + 1;
			g.data[g.index++] = d;
			if (d.initial) {
				g.index = 0
			}
			if (g.data.length == 2 && g.data[0].initial) {
				g.data[0].bookmark = c
			}
			g.onAdd.dispatch(g, d);
			e.isNotDirty = 0;
			return d
		},
		undo : function() {
			var e = this, c = e.editor, b = b, d;
			if (e.typing) {
				e.add();
				e.typing = 0
			}
			if (e.index > 0) {
				if (e.index == e.data.length && e.index > 1) {
					d = e.index;
					e.typing = 0;
					if (!e.add()) {
						e.index = d
					}
					--e.index
				}
				b = e.data[--e.index];
				c.setContent(b.content, {
					format :"raw"
				});
				c.selection.moveToBookmark(b.bookmark);
				e.onUndo.dispatch(e, b)
			}
			return b
		},
		redo : function() {
			var d = this, c = d.editor, b = null;
			if (d.index < d.data.length - 1) {
				b = d.data[++d.index];
				c.setContent(b.content, {
					format :"raw"
				});
				c.selection.moveToBookmark(b.bookmark);
				d.onRedo.dispatch(d, b)
			}
			return b
		},
		clear : function() {
			var b = this;
			b.data = [];
			b.index = 0;
			b.typing = 0;
			b.add( {
				initial :true
			})
		},
		hasUndo : function() {
			return this.index != 0 || this.typing
		},
		hasRedo : function() {
			return this.index < this.data.length - 1
		}
	})
})(tinymce);
( function(e) {
	var b, d, a, c, f, g;
	b = e.dom.Event;
	d = e.isIE;
	a = e.isGecko;
	c = e.isOpera;
	f = e.each;
	g = e.extend;
	e
			.create(
					"tinymce.ForceBlocks",
					{
						ForceBlocks : function(i) {
							var j = this, k = i.settings, l;
							j.editor = i;
							j.dom = i.dom;
							l = (k.forced_root_block || "p").toLowerCase();
							k.element = l.toUpperCase();
							i.onPreInit.add(j.setup, j);
							j.reOpera = new RegExp("(\\u00a0|&#160;|&nbsp;)</"
									+ l + ">", "gi");
							j.rePadd = new RegExp(
									"<p( )([^>]+)><\\/p>|<p( )([^>]+)\\/>|<p( )([^>]+)>\\s+<\\/p>|<p><\\/p>|<p\\/>|<p>\\s+<\\/p>"
											.replace(/p/g, l), "gi");
							j.reNbsp2BR1 = new RegExp(
									"<p( )([^>]+)>[\\s\\u00a0]+<\\/p>|<p>[\\s\\u00a0]+<\\/p>"
											.replace(/p/g, l), "gi");
							j.reNbsp2BR2 = new RegExp(
									"<%p()([^>]+)>(&nbsp;|&#160;)<\\/%p>|<%p>(&nbsp;|&#160;)<\\/%p>"
											.replace(/%p/g, l), "gi");
							j.reBR2Nbsp = new RegExp(
									"<p( )([^>]+)>\\s*<br \\/>\\s*<\\/p>|<p>\\s*<br \\/>\\s*<\\/p>"
											.replace(/p/g, l), "gi");
							j.reTrailBr = new RegExp("\\s*<br \\/>\\s*<\\/p>"
									.replace(/p/g, l), "gi");
							function h(m, n) {
								if (c) {
									n.content = n.content.replace(j.reOpera,
											"</" + l + ">")
								}
								n.content = n.content.replace(j.rePadd, "<" + l
										+ "$1$2$3$4$5$6>\u00a0</" + l + ">");
								if (!d && !c && n.set) {
									n.content = n.content
											.replace(j.reNbsp2BR1, "<" + l
													+ "$1$2><br /></" + l + ">");
									n.content = n.content
											.replace(j.reNbsp2BR2, "<" + l
													+ "$1$2><br /></" + l + ">")
								} else {
									n.content = n.content
											.replace(j.reBR2Nbsp, "<" + l
													+ "$1$2>\u00a0</" + l + ">");
									n.content = n.content.replace(j.reTrailBr,
											"</" + l + ">")
								}
							}
							i.onBeforeSetContent.add(h);
							i.onPostProcess.add(h);
							if (k.forced_root_block) {
								i.onInit.add(j.forceRoots, j);
								i.onSetContent.add(j.forceRoots, j);
								i.onBeforeGetContent.add(j.forceRoots, j)
							}
						},
						setup : function() {
							var i = this, h = i.editor, j = h.settings;
							if (j.forced_root_block) {
								h.onKeyUp.add(i.forceRoots, i);
								h.onPreProcess.add(i.forceRoots, i)
							}
							if (j.force_br_newlines) {
								if (d) {
									h.onKeyPress
											.add( function(l, o) {
												var p, m = l.selection;
												if (o.keyCode == 13
														&& m.getNode().nodeName != "LI") {
													m.setContent(
															'<br id="__" /> ',
															{
																format :"raw"
															});
													p = l.dom.get("__");
													p.removeAttribute("id");
													m.select(p);
													m.collapse();
													return b.cancel(o)
												}
											})
								}
								return
							}
							if (!d && j.force_p_newlines) {
								h.onKeyPress.add( function(l, m) {
									if (m.keyCode == 13 && !m.shiftKey) {
										if (!i.insertPara(m)) {
											b.cancel(m)
										}
									}
								});
								if (a) {
									h.onKeyDown.add( function(l, m) {
										if ((m.keyCode == 8 || m.keyCode == 46)
												&& !m.shiftKey) {
											i
													.backspaceDelete(m,
															m.keyCode == 8)
										}
									})
								}
							}
							function k(m, l) {
								var n = h.dom.create(l);
								f(m.attributes, function(o) {
									if (o.specified && o.nodeValue) {
										n.setAttribute(
												o.nodeName.toLowerCase(),
												o.nodeValue)
									}
								});
								f(m.childNodes, function(o) {
									n.appendChild(o.cloneNode(true))
								});
								m.parentNode.replaceChild(n, m);
								return n
							}
							if (d && j.element != "P") {
								h.onKeyPress.add( function(l, m) {
									i.lastElm = l.selection.getNode().nodeName
								});
								h.onKeyUp
										.add( function(m, p) {
											var r, o = m.selection, q = o
													.getNode(), l = m.getBody();
											if (l.childNodes.length === 1
													&& q.nodeName == "P") {
												q = k(q, j.element);
												o.select(q);
												o.collapse();
												m.nodeChanged()
											} else {
												if (p.keyCode == 13
														&& !p.shiftKey
														&& i.lastElm != "P") {
													r = m.dom.getParent(q, "P");
													if (r) {
														k(r, j.element);
														m.nodeChanged()
													}
												}
											}
										})
							}
						},
						find : function(m, j, k) {
							var i = this.editor, h = i.getDoc()
									.createTreeWalker(m, 4, null, false), l = -1;
							while (m = h.nextNode()) {
								l++;
								if (j == 0 && m == k) {
									return l
								}
								if (j == 1 && l == k) {
									return m
								}
							}
							return -1
						},
						forceRoots : function(o, C) {
							var q = this, o = q.editor, G = o.getBody(), D = o
									.getDoc(), J = o.selection, u = J.getSel(), v = J
									.getRng(), H = -2, m, A, h, j, E = -16777215;
							var F, k, I, z, w, l = G.childNodes, y, x, p;
							for (y = l.length - 1; y >= 0; y--) {
								F = l[y];
								if (F.nodeType == 3
										|| (!q.dom.isBlock(F) && F.nodeType != 8)) {
									if (!k) {
										if (F.nodeType != 3
												|| /[^\s]/g.test(F.nodeValue)) {
											if (H == -2 && v) {
												if (!d) {
													if (v.startContainer.nodeType == 1
															&& (x = v.startContainer.childNodes[v.startOffset])
															&& x.nodeType == 1) {
														p = x
																.getAttribute("id");
														x.setAttribute("id",
																"__mce")
													} else {
														if (o.dom
																.getParent(
																		v.startContainer,
																		function(
																				i) {
																			return i === G
																		})) {
															A = v.startOffset;
															h = v.endOffset;
															H = q
																	.find(
																			G,
																			0,
																			v.startContainer);
															m = q
																	.find(
																			G,
																			0,
																			v.endContainer)
														}
													}
												} else {
													j = D.body
															.createTextRange();
													j.moveToElementText(G);
													j.collapse(1);
													I = j.move("character", E)
															* -1;
													j = v.duplicate();
													j.collapse(1);
													z = j.move("character", E)
															* -1;
													j = v.duplicate();
													j.collapse(0);
													w = (j.move("character", E) * -1)
															- z;
													H = z - I;
													m = w
												}
											}
											k = o.dom
													.create(o.settings.forced_root_block);
											k.appendChild(F.cloneNode(1));
											F.parentNode.replaceChild(k, F)
										}
									} else {
										if (k.hasChildNodes()) {
											k.insertBefore(F, k.firstChild)
										} else {
											k.appendChild(F)
										}
									}
								} else {
									k = null
								}
							}
							if (H != -2) {
								if (!d) {
									k = G
											.getElementsByTagName(o.settings.element)[0];
									v = D.createRange();
									if (H != -1) {
										v.setStart(q.find(G, 1, H), A)
									} else {
										v.setStart(k, 0)
									}
									if (m != -1) {
										v.setEnd(q.find(G, 1, m), h)
									} else {
										v.setEnd(k, 0)
									}
									if (u) {
										u.removeAllRanges();
										u.addRange(v)
									}
								} else {
									try {
										v = u.createRange();
										v.moveToElementText(G);
										v.collapse(1);
										v.moveStart("character", H);
										v.moveEnd("character", m);
										v.select()
									} catch (B) {
									}
								}
							} else {
								if (!d && (x = o.dom.get("__mce"))) {
									if (p) {
										x.setAttribute("id", p)
									} else {
										x.removeAttribute("id")
									}
									v = D.createRange();
									v.setStartBefore(x);
									v.setEndBefore(x);
									J.setRng(v)
								}
							}
						},
						getParentBlock : function(i) {
							var h = this.dom;
							return h.getParent(i, h.isBlock)
						},
						insertPara : function(M) {
							var z = this, m = z.editor, I = m.dom, N = m
									.getDoc(), R = m.settings, B = m.selection
									.getSel(), C = B.getRangeAt(0), Q = N.body;
							var F, G, D, K, J, k, i, l, p, h, w, P, j, o, E, H = I
									.getViewPort(m.getWin()), v, x, u;
							function A(r) {
								r = r.innerHTML;
								r = r.replace(/<(img|hr|table)/gi, "-");
								r = r.replace(/<[^>]+>/g, "");
								return r.replace(/[ \t\r\n]+/g, "") == ""
							}
							F = N.createRange();
							F.setStart(B.anchorNode, B.anchorOffset);
							F.collapse(true);
							G = N.createRange();
							G.setStart(B.focusNode, B.focusOffset);
							G.collapse(true);
							D = F.compareBoundaryPoints(F.START_TO_END, G) < 0;
							K = D ? B.anchorNode : B.focusNode;
							J = D ? B.anchorOffset : B.focusOffset;
							k = D ? B.focusNode : B.anchorNode;
							i = D ? B.focusOffset : B.anchorOffset;
							if (K === k && /^(TD|TH)$/.test(K.nodeName)) {
								I.remove(K.firstChild);
								m.dom.add(K, R.element, null, "<br />");
								P = m.dom.add(K, R.element, null, "<br />");
								C = N.createRange();
								C.selectNodeContents(P);
								C.collapse(1);
								m.selection.setRng(C);
								return false
							}
							if (K == Q && k == Q && Q.firstChild
									&& m.dom.isBlock(Q.firstChild)) {
								K = k = K.firstChild;
								J = i = 0;
								F = N.createRange();
								F.setStart(K, 0);
								G = N.createRange();
								G.setStart(k, 0)
							}
							K = K.nodeName == "HTML" ? N.body : K;
							K = K.nodeName == "BODY" ? K.firstChild : K;
							k = k.nodeName == "HTML" ? N.body : k;
							k = k.nodeName == "BODY" ? k.firstChild : k;
							l = z.getParentBlock(K);
							p = z.getParentBlock(k);
							h = l ? l.nodeName : R.element;
							if (z.dom.getParent(l, "OL,UL,PRE")) {
								return true
							}
							if (l
									&& (l.nodeName == "CAPTION" || /absolute|relative|fixed/gi
											.test(I.getStyle(l, "position", 1)))) {
								h = R.element;
								l = null
							}
							if (p
									&& (p.nodeName == "CAPTION" || /absolute|relative|fixed/gi
											.test(I.getStyle(l, "position", 1)))) {
								h = R.element;
								p = null
							}
							if (/(TD|TABLE|TH|CAPTION)/.test(h)
									|| (l && h == "DIV" && /left|right/gi
											.test(I.getStyle(l, "float", 1)))) {
								h = R.element;
								l = p = null
							}
							w = (l && l.nodeName == h) ? l.cloneNode(0) : m.dom
									.create(h);
							P = (p && p.nodeName == h) ? p.cloneNode(0) : m.dom
									.create(h);
							P.removeAttribute("id");
							if (/^(H[1-6])$/.test(h) && K.nodeValue
									&& J == K.nodeValue.length) {
								P = m.dom.create(R.element)
							}
							E = j = K;
							do {
								if (E == Q
										|| E.nodeType == 9
										|| z.dom.isBlock(E)
										|| /(TD|TABLE|TH|CAPTION)/
												.test(E.nodeName)) {
									break
								}
								j = E
							} while ((E = E.previousSibling ? E.previousSibling
									: E.parentNode));
							E = o = k;
							do {
								if (E == Q
										|| E.nodeType == 9
										|| z.dom.isBlock(E)
										|| /(TD|TABLE|TH|CAPTION)/
												.test(E.nodeName)) {
									break
								}
								o = E
							} while ((E = E.nextSibling ? E.nextSibling
									: E.parentNode));
							if (j.nodeName == h) {
								F.setStart(j, 0)
							} else {
								F.setStartBefore(j)
							}
							F.setEnd(K, J);
							w.appendChild(F.cloneContents()
									|| N.createTextNode(""));
							try {
								G.setEndAfter(o)
							} catch (L) {
							}
							G.setStart(k, i);
							P.appendChild(G.cloneContents()
									|| N.createTextNode(""));
							C = N.createRange();
							if (!j.previousSibling
									&& j.parentNode.nodeName == h) {
								C.setStartBefore(j.parentNode)
							} else {
								if (F.startContainer.nodeName == h
										&& F.startOffset == 0) {
									C.setStartBefore(F.startContainer)
								} else {
									C.setStart(F.startContainer, F.startOffset)
								}
							}
							if (!o.nextSibling && o.parentNode.nodeName == h) {
								C.setEndAfter(o.parentNode)
							} else {
								C.setEnd(G.endContainer, G.endOffset)
							}
							C.deleteContents();
							if (c) {
								m.getWin().scrollTo(0, H.y)
							}
							if (w.firstChild && w.firstChild.nodeName == h) {
								w.innerHTML = w.firstChild.innerHTML
							}
							if (P.firstChild && P.firstChild.nodeName == h) {
								P.innerHTML = P.firstChild.innerHTML
							}
							if (A(w)) {
								w.innerHTML = "<br />"
							}
							function O(y, s) {
								var r = [], T, S, t;
								y.innerHTML = "";
								if (R.keep_styles) {
									S = s;
									do {
										if (/^(SPAN|STRONG|B|EM|I|FONT|STRIKE|U)$/
												.test(S.nodeName)) {
											T = S.cloneNode(false);
											I.setAttrib(T, "id", "");
											r.push(T)
										}
									} while (S = S.parentNode)
								}
								if (r.length > 0) {
									for (t = r.length - 1, T = y; t >= 0; t--) {
										T = T.appendChild(r[t])
									}
									r[0].innerHTML = c ? "&nbsp;" : "<br />";
									return r[0]
								} else {
									y.innerHTML = c ? "&nbsp;" : "<br />"
								}
							}
							if (A(P)) {
								u = O(P, k)
							}
							if (c && parseFloat(opera.version()) < 9.5) {
								C.insertNode(w);
								C.insertNode(P)
							} else {
								C.insertNode(P);
								C.insertNode(w)
							}
							P.normalize();
							w.normalize();
							function q(r) {
								return N.createTreeWalker(r,
										NodeFilter.SHOW_TEXT, null, false)
										.nextNode()
										|| r
							}
							C = N.createRange();
							C.selectNodeContents(a ? q(u || P) : u || P);
							C.collapse(1);
							B.removeAllRanges();
							B.addRange(C);
							v = m.dom.getPos(P).y;
							x = P.clientHeight;
							if (v < H.y || v + x > H.y + H.h) {
								m.getWin().scrollTo(0,
										v < H.y ? v : v - H.h + 25)
							}
							return false
						},
						backspaceDelete : function(k, s) {
							var v = this, j = v.editor, o = j.getBody(), i, l = j.selection, h = l
									.getRng(), m = h.startContainer, i, p, q;
							if (m && j.dom.isBlock(m)
									&& !/^(TD|TH)$/.test(m.nodeName) && s) {
								if (m.childNodes.length == 0
										|| (m.childNodes.length == 1 && m.firstChild.nodeName == "BR")) {
									i = m;
									while ((i = i.previousSibling)
											&& !j.dom.isBlock(i)) {
									}
									if (i) {
										if (m != o.firstChild) {
											p = j.dom.doc.createTreeWalker(i,
													NodeFilter.SHOW_TEXT, null,
													false);
											while (q = p.nextNode()) {
												i = q
											}
											h = j.getDoc().createRange();
											h
													.setStart(
															i,
															i.nodeValue ? i.nodeValue.length
																	: 0);
											h
													.setEnd(
															i,
															i.nodeValue ? i.nodeValue.length
																	: 0);
											l.setRng(h);
											j.dom.remove(m)
										}
										return b.cancel(k)
									}
								}
							}
							function u(n) {
								var r;
								n = n.target;
								if (n && n.parentNode && n.nodeName == "BR"
										&& (i = v.getParentBlock(n))) {
									r = n.previousSibling;
									b.remove(o, "DOMNodeInserted", u);
									if (r && r.nodeType == 3
											&& /\s+$/.test(r.nodeValue)) {
										return
									}
									if (n.previousSibling || n.nextSibling) {
										j.dom.remove(n)
									}
								}
							}
							b._add(o, "DOMNodeInserted", u);
							window.setTimeout( function() {
								b._remove(o, "DOMNodeInserted", u)
							}, 1)
						}
					})
})(tinymce);
( function(c) {
	var b = c.DOM, a = c.dom.Event, d = c.each, e = c.extend;
	c.create("tinymce.ControlManager", {
		ControlManager : function(f, j) {
			var h = this, g;
			j = j || {};
			h.editor = f;
			h.controls = {};
			h.onAdd = new c.util.Dispatcher(h);
			h.onPostRender = new c.util.Dispatcher(h);
			h.prefix = j.prefix || f.id + "_";
			h._cls = {};
			h.onPostRender.add( function() {
				d(h.controls, function(i) {
					i.postRender()
				})
			})
		},
		get : function(f) {
			return this.controls[this.prefix + f] || this.controls[f]
		},
		setActive : function(h, f) {
			var g = null;
			if (g = this.get(h)) {
				g.setActive(f)
			}
			return g
		},
		setDisabled : function(h, f) {
			var g = null;
			if (g = this.get(h)) {
				g.setDisabled(f)
			}
			return g
		},
		add : function(g) {
			var f = this;
			if (g) {
				f.controls[g.id] = g;
				f.onAdd.dispatch(g, f)
			}
			return g
		},
		createControl : function(i) {
			var h, g = this, f = g.editor;
			d(f.plugins, function(j) {
				if (j.createControl) {
					h = j.createControl(i, g);
					if (h) {
						return false
					}
				}
			});
			switch (i) {
			case "|":
			case "separator":
				return g.createSeparator()
			}
			if (!h && f.buttons && (h = f.buttons[i])) {
				return g.createButton(i, h)
			}
			return g.add(h)
		},
		createDropMenu : function(f, n, h) {
			var m = this, i = m.editor, j, g, k, l;
			n = e( {
				"class" :"mceDropDown",
				constrain :i.settings.constrain_menus
			}, n);
			n["class"] = n["class"] + " " + i.getParam("skin") + "Skin";
			if (k = i.getParam("skin_variant")) {
				n["class"] += " " + i.getParam("skin") + "Skin"
						+ k.substring(0, 1).toUpperCase() + k.substring(1)
			}
			f = m.prefix + f;
			l = h || m._cls.dropmenu || c.ui.DropMenu;
			j = m.controls[f] = new l(f, n);
			j.onAddItem.add( function(r, q) {
				var p = q.settings;
				p.title = i.getLang(p.title, p.title);
				if (!p.onclick) {
					p.onclick = function(o) {
						i.execCommand(p.cmd, p.ui || false, p.value)
					}
				}
			});
			i.onRemove.add( function() {
				j.destroy()
			});
			if (c.isIE) {
				j.onShowMenu.add( function() {
					g = i.selection.getBookmark(1)
				});
				j.onHideMenu.add( function() {
					if (g) {
						i.selection.moveToBookmark(g);
						g = 0
					}
				})
			}
			return m.add(j)
		},
		createListBox : function(m, i, l) {
			var h = this, g = h.editor, j, k, f;
			if (h.get(m)) {
				return null
			}
			i.title = g.translate(i.title);
			i.scope = i.scope || g;
			if (!i.onselect) {
				i.onselect = function(n) {
					g.execCommand(i.cmd, i.ui || false, n || i.value)
				}
			}
			i = e( {
				title :i.title,
				"class" :"mce_" + m,
				scope :i.scope,
				control_manager :h
			}, i);
			m = h.prefix + m;
			if (g.settings.use_native_selects) {
				k = new c.ui.NativeListBox(m, i)
			} else {
				f = l || h._cls.listbox || c.ui.ListBox;
				k = new f(m, i)
			}
			h.controls[m] = k;
			if (c.isWebKit) {
				k.onPostRender.add( function(p, o) {
					a.add(o, "mousedown", function() {
						g.bookmark = g.selection.getBookmark("simple")
					});
					a.add(o, "focus", function() {
						g.selection.moveToBookmark(g.bookmark);
						g.bookmark = null
					})
				})
			}
			if (k.hideMenu) {
				g.onMouseDown.add(k.hideMenu, k)
			}
			return h.add(k)
		},
		createButton : function(m, i, l) {
			var h = this, g = h.editor, j, k, f;
			if (h.get(m)) {
				return null
			}
			i.title = g.translate(i.title);
			i.label = g.translate(i.label);
			i.scope = i.scope || g;
			if (!i.onclick && !i.menu_button) {
				i.onclick = function() {
					g.execCommand(i.cmd, i.ui || false, i.value)
				}
			}
			i = e( {
				title :i.title,
				"class" :"mce_" + m,
				unavailable_prefix :g.getLang("unavailable", ""),
				scope :i.scope,
				control_manager :h
			}, i);
			m = h.prefix + m;
			if (i.menu_button) {
				f = l || h._cls.menubutton || c.ui.MenuButton;
				k = new f(m, i);
				g.onMouseDown.add(k.hideMenu, k)
			} else {
				f = h._cls.button || c.ui.Button;
				k = new f(m, i)
			}
			return h.add(k)
		},
		createMenuButton : function(h, f, g) {
			f = f || {};
			f.menu_button = 1;
			return this.createButton(h, f, g)
		},
		createSplitButton : function(m, i, l) {
			var h = this, g = h.editor, j, k, f;
			if (h.get(m)) {
				return null
			}
			i.title = g.translate(i.title);
			i.scope = i.scope || g;
			if (!i.onclick) {
				i.onclick = function(n) {
					g.execCommand(i.cmd, i.ui || false, n || i.value)
				}
			}
			if (!i.onselect) {
				i.onselect = function(n) {
					g.execCommand(i.cmd, i.ui || false, n || i.value)
				}
			}
			i = e( {
				title :i.title,
				"class" :"mce_" + m,
				scope :i.scope,
				control_manager :h
			}, i);
			m = h.prefix + m;
			f = l || h._cls.splitbutton || c.ui.SplitButton;
			k = h.add(new f(m, i));
			g.onMouseDown.add(k.hideMenu, k);
			return k
		},
		createColorSplitButton : function(f, n, h) {
			var l = this, j = l.editor, i, k, m, g;
			if (l.get(f)) {
				return null
			}
			n.title = j.translate(n.title);
			n.scope = n.scope || j;
			if (!n.onclick) {
				n.onclick = function(o) {
					if (c.isIE) {
						g = j.selection.getBookmark(1)
					}
					j.execCommand(n.cmd, n.ui || false, o || n.value)
				}
			}
			if (!n.onselect) {
				n.onselect = function(o) {
					j.execCommand(n.cmd, n.ui || false, o || n.value)
				}
			}
			n = e( {
				title :n.title,
				"class" :"mce_" + f,
				menu_class :j.getParam("skin") + "Skin",
				scope :n.scope,
				more_colors_title :j.getLang("more_colors")
			}, n);
			f = l.prefix + f;
			m = h || l._cls.colorsplitbutton || c.ui.ColorSplitButton;
			k = new m(f, n);
			j.onMouseDown.add(k.hideMenu, k);
			j.onRemove.add( function() {
				k.destroy()
			});
			if (c.isIE) {
				k.onHideMenu.add( function() {
					if (g) {
						j.selection.moveToBookmark(g);
						g = 0
					}
				})
			}
			return l.add(k)
		},
		createToolbar : function(k, h, j) {
			var i, g = this, f;
			k = g.prefix + k;
			f = j || g._cls.toolbar || c.ui.Toolbar;
			i = new f(k, h);
			if (g.get(k)) {
				return null
			}
			return g.add(i)
		},
		createSeparator : function(g) {
			var f = g || this._cls.separator || c.ui.Separator;
			return new f()
		},
		setControlType : function(g, f) {
			return this._cls[g.toLowerCase()] = f
		},
		destroy : function() {
			d(this.controls, function(f) {
				f.destroy()
			});
			this.controls = null
		}
	})
})(tinymce);
( function(d) {
	var a = d.util.Dispatcher, e = d.each, c = d.isIE, b = d.isOpera;
	d
			.create(
					"tinymce.WindowManager",
					{
						WindowManager : function(f) {
							var g = this;
							g.editor = f;
							g.onOpen = new a(g);
							g.onClose = new a(g);
							g.params = {};
							g.features = {}
						},
						open : function(z, h) {
							var v = this, k = "", n, m, i = v.editor.settings.dialog_type == "modal", q, o, j, g = d.DOM
									.getViewPort(), r;
							z = z || {};
							h = h || {};
							o = b ? g.w : screen.width;
							j = b ? g.h : screen.height;
							z.name = z.name || "mc_" + new Date().getTime();
							z.width = parseInt(z.width || 320);
							z.height = parseInt(z.height || 240);
							z.resizable = true;
							z.left = z.left || parseInt(o / 2) - (z.width / 2);
							z.top = z.top || parseInt(j / 2) - (z.height / 2);
							h.inline = false;
							h.mce_width = z.width;
							h.mce_height = z.height;
							h.mce_auto_focus = z.auto_focus;
							if (i) {
								if (c) {
									z.center = true;
									z.help = false;
									z.dialogWidth = z.width + "px";
									z.dialogHeight = z.height + "px";
									z.scroll = z.scrollbars || false
								}
							}
							e(z, function(p, f) {
								if (d.is(p, "boolean")) {
									p = p ? "yes" : "no"
								}
								if (!/^(name|url)$/.test(f)) {
									if (c && i) {
										k += (k ? ";" : "") + f + ":" + p
									} else {
										k += (k ? "," : "") + f + "=" + p
									}
								}
							});
							v.features = z;
							v.params = h;
							v.onOpen.dispatch(v, z, h);
							r = z.url || z.file;
							r = d._addVer(r);
							try {
								if (c && i) {
									q = 1;
									window.showModalDialog(r, window, k)
								} else {
									q = window.open(r, z.name, k)
								}
							} catch (l) {
							}
							if (!q) {
								alert(v.editor.getLang("popup_blocked"))
							}
						},
						close : function(f) {
							f.close();
							this.onClose.dispatch(this)
						},
						createInstance : function(i, h, g, m, l, k) {
							var j = d.resolve(i);
							return new j(h, g, m, l, k)
						},
						confirm : function(h, f, i, g) {
							g = g || window;
							f.call(i || this, g.confirm(this
									._decode(this.editor.getLang(h, h))))
						},
						alert : function(h, f, j, g) {
							var i = this;
							g = g || window;
							g.alert(i._decode(i.editor.getLang(h, h)));
							if (f) {
								f.call(j || i)
							}
						},
						_decode : function(f) {
							return d.DOM.decode(f).replace(/\\n/g, "\n")
						}
					})
}(tinymce));
( function(a) {
	a.CommandManager = function() {
		var c = {}, b = {}, d = {};
		function e(i, h, g, f) {
			if (typeof (h) == "string") {
				h = [ h ]
			}
			a.each(h, function(j) {
				i[j.toLowerCase()] = {
					func :g,
					scope :f
				}
			})
		}
		a.extend(this, {
			add : function(h, g, f) {
				e(c, h, g, f)
			},
			addQueryStateHandler : function(h, g, f) {
				e(b, h, g, f)
			},
			addQueryValueHandler : function(h, g, f) {
				e(d, h, g, f)
			},
			execCommand : function(g, j, i, h, f) {
				if (j = c[j.toLowerCase()]) {
					if (j.func.call(g || j.scope, i, h, f) !== false) {
						return true
					}
				}
			},
			queryCommandValue : function() {
				if (cmd = d[cmd.toLowerCase()]) {
					return cmd.func.call(scope || cmd.scope, ui, value, args)
				}
			},
			queryCommandState : function() {
				if (cmd = b[cmd.toLowerCase()]) {
					return cmd.func.call(scope || cmd.scope, ui, value, args)
				}
			}
		})
	};
	a.GlobalCommands = new a.CommandManager()
})(tinymce);
( function(b) {
	function a(i, d, h, m) {
		var j, g, e, l, f;
		function k(p, o) {
			do {
				if (p.parentNode == o) {
					return p
				}
				p = p.parentNode
			} while (p)
		}
		function c(o) {
			m(o);
			b.walk(o, m, "childNodes")
		}
		j = i.findCommonAncestor(d, h);
		e = k(d, j) || d;
		l = k(h, j) || h;
		for (g = d; g && g != e; g = g.parentNode) {
			for (f = g.nextSibling; f; f = f.nextSibling) {
				c(f)
			}
		}
		if (e != l) {
			for (g = e.nextSibling; g && g != l; g = g.nextSibling) {
				c(g)
			}
		} else {
			c(e)
		}
		for (g = h; g && g != l; g = g.parentNode) {
			for (f = g.previousSibling; f; f = f.previousSibling) {
				c(f)
			}
		}
	}
	b.GlobalCommands
			.add(
					"RemoveFormat",
					function() {
						var m = this, l = m.dom, u = m.selection, d = u
								.getRng(1), e = [], h, f, j, q, g, o, c, i;
						function k(s) {
							var r;
							l
									.getParent(
											s,
											function(v) {
												if (l
														.is(
																v,
																m
																		.getParam("removeformat_selector"))) {
													r = v
												}
												return l.isBlock(v)
											}, m.getBody());
							return r
						}
						function p(r) {
							if (l.is(r, m.getParam("removeformat_selector"))) {
								e.push(r)
							}
						}
						function t(r) {
							p(r);
							b.walk(r, p, "childNodes")
						}
						h = u.getBookmark();
						q = d.startContainer;
						o = d.endContainer;
						g = d.startOffset;
						c = d.endOffset;
						q = q.nodeType == 1 ? q.childNodes[g] : q;
						o = o.nodeType == 1 ? o.childNodes[c - 1] : o;
						if (q == o) {
							f = k(q);
							if (q.nodeType == 3) {
								if (f && f.nodeType == 1) {
									i = q.splitText(g);
									i.splitText(c - g);
									l.split(f, i);
									u.moveToBookmark(h)
								}
								return
							}
							t(l.split(f, q) || q)
						} else {
							f = k(q);
							j = k(o);
							if (f) {
								if (q.nodeType == 3) {
									if (g == q.nodeValue.length) {
										q.nodeValue += "\uFEFF"
									}
									q = q.splitText(g)
								}
							}
							if (j) {
								if (o.nodeType == 3) {
									o.splitText(c)
								}
							}
							if (f && f == j) {
								l.replace(l.create("span", {
									id :"__end"
								}, o.cloneNode(true)), o)
							}
							if (f) {
								f = l.split(f, q)
							} else {
								f = q
							}
							if (i = l.get("__end")) {
								o = i;
								j = k(o)
							}
							if (j) {
								j = l.split(j, o)
							} else {
								j = o
							}
							a(l, f, j, p);
							if (q.nodeValue == "\uFEFF") {
								q.nodeValue = ""
							}
							t(o);
							t(q)
						}
						b.each(e, function(r) {
							l.remove(r, 1)
						});
						l.remove("__end", 1);
						u.moveToBookmark(h)
					})
})(tinymce);
( function(a) {
	a.GlobalCommands
			.add(
					"mceBlockQuote",
					function() {
						var j = this, o = j.selection, f = j.dom, l, k, e, d, p, c, m, h, b;
						function g(i) {
							return f.getParent(i, function(q) {
								return q.nodeName === "BLOCKQUOTE"
							})
						}
						l = f.getParent(o.getStart(), f.isBlock);
						k = f.getParent(o.getEnd(), f.isBlock);
						if (p = g(l)) {
							if (l != k
									|| l.childNodes.length > 1
									|| (l.childNodes.length == 1 && l.firstChild.nodeName != "BR")) {
								d = o.getBookmark()
							}
							if (g(k)) {
								m = p.cloneNode(false);
								while (e = k.nextSibling) {
									m.appendChild(e.parentNode.removeChild(e))
								}
							}
							if (m) {
								f.insertAfter(m, p)
							}
							b = o.getSelectedBlocks(l, k);
							for (h = b.length - 1; h >= 0; h--) {
								f.insertAfter(b[h], p)
							}
							if (/^\s*$/.test(p.innerHTML)) {
								f.remove(p, 1)
							}
							if (m && /^\s*$/.test(m.innerHTML)) {
								f.remove(m, 1)
							}
							if (!d) {
								if (!a.isIE) {
									c = j.getDoc().createRange();
									c.setStart(l, 0);
									c.setEnd(l, 0);
									o.setRng(c)
								} else {
									o.select(l);
									o.collapse(0);
									if (f.getParent(o.getStart(), f.isBlock) != l) {
										c = o.getRng();
										c.move("character", -1);
										c.select()
									}
								}
							} else {
								j.selection.moveToBookmark(d)
							}
							return
						}
						if (a.isIE && !l && !k) {
							j.getDoc().execCommand("Indent");
							e = g(o.getNode());
							e.style.margin = e.dir = "";
							return
						}
						if (!l || !k) {
							return
						}
						if (l != k
								|| l.childNodes.length > 1
								|| (l.childNodes.length == 1 && l.firstChild.nodeName != "BR")) {
							d = o.getBookmark()
						}
						a.each(o.getSelectedBlocks(g(o.getStart()), g(o
								.getEnd())), function(i) {
							if (i.nodeName == "BLOCKQUOTE" && !p) {
								p = i;
								return
							}
							if (!p) {
								p = f.create("blockquote");
								i.parentNode.insertBefore(p, i)
							}
							if (i.nodeName == "BLOCKQUOTE" && p) {
								e = i.firstChild;
								while (e) {
									p.appendChild(e.cloneNode(true));
									e = e.nextSibling
								}
								f.remove(i);
								return
							}
							p.appendChild(f.remove(i))
						});
						if (!d) {
							if (!a.isIE) {
								c = j.getDoc().createRange();
								c.setStart(l, 0);
								c.setEnd(l, 0);
								o.setRng(c)
							} else {
								o.select(l);
								o.collapse(1)
							}
						} else {
							o.moveToBookmark(d)
						}
					})
})(tinymce);
( function(a) {
	a.each( [ "Cut", "Copy", "Paste" ], function(b) {
		a.GlobalCommands.add(b, function() {
			var c = this, e = c.getDoc();
			try {
				e.execCommand(b, false, null);
				if (!e.queryCommandSupported(b)) {
					throw "Error"
				}
			} catch (d) {
				c.windowManager.alert(c.getLang("clipboard_no_support"))
			}
		})
	})
})(tinymce);
( function(a) {
	a.GlobalCommands.add("InsertHorizontalRule", function() {
		if (a.isOpera) {
			return this.getDoc().execCommand("InsertHorizontalRule", false, "")
		}
		this.selection.setContent("<hr />")
	})
})(tinymce);
( function() {
	var a = tinymce.GlobalCommands;
	a.add( [ "mceEndUndoLevel", "mceAddUndoLevel" ], function() {
		this.undoManager.add()
	});
	a.add("Undo", function() {
		var b = this;
		if (b.settings.custom_undo_redo) {
			b.undoManager.undo();
			b.nodeChanged();
			return true
		}
		return false
	});
	a.add("Redo", function() {
		var b = this;
		if (b.settings.custom_undo_redo) {
			b.undoManager.redo();
			b.nodeChanged();
			return true
		}
		return false
	})
})();