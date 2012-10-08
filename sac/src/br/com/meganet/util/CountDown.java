package br.com.meganet.util;



public class CountDown extends Thread{
	
	private int hor = 1;
	private int min = 1;
	private int sec = 0;
	private boolean rodar = true;
	private static Logger logger = new Logger(CountDown.class);
	
	public void run(){
		try {
			while(rodar){
				Thread.sleep(1000);
				if(sec > 0){
					sec--;
				}else{
					sec = 59;
					if(min > 0){
						min--;
					}else{
						min = 59;
						sec = 59;
						if(hor > 0){
							hor--;
						}else{
							rodar = false;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.erro("Erro no contador de RSS", e);
		}
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public void setRodar(boolean rodar) {
		this.rodar = rodar;
	}

	public int getHor() {
		return hor;
	}

	public void setHor(int hor) {
		this.hor = hor;
	}

	public boolean estaRodando() {
		return rodar;
	}
	
	public static void main(String[] args){
		CountDown cd = new CountDown();
		cd.start();
	}

}
