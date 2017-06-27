package text2;

public class Des extends Feistel {
	
	Des(){
		super();
	}
	
	public Des(int number){
		super(number);
	}
	
	/* 
	 * �ֺ���*/
	String F(String str, String key){
		String Fstr = "";
		int E_Table_L = Constant.E_Table.length;
		for(int i =0; i < E_Table_L; i++){	//��E����
			Fstr += str.charAt(Constant.E_Table[i]);
		}
		
		String xor = "";
		for(int i = 0; i < Fstr.length(); i++){	//��key�������
			if(Fstr.charAt(i) == key.charAt(i))
				xor += "0";
			else
				xor += "1";
		}
		
		int Snumber = Constant.S_Table.length;
		String S = "";
		for(int i = 0; i < Snumber; i++){	//S�д���
			String temp = "";
			for(int j = i*6; j < (i+1)*6; j++){
				temp += xor.charAt(j);
			}
			int xnum = 0, ynum =0;
			String x1 = "", x2 ="";
			x1 += temp.charAt(0);
			x2 += temp.charAt(5);
			xnum = Integer.parseInt(x1)*2 + Integer.parseInt(x2);
			String y1 = "", y2 = "", y3 = "", y4 = "";
			y1 += temp.charAt(1);
			y2 += temp.charAt(2);
			y3 += temp.charAt(3);
			y4 += temp.charAt(4);
			ynum = Integer.parseInt(y1)*8 + Integer.parseInt(y2)*4 + Integer.parseInt(y3)*2 + Integer.parseInt(y4);
			
			int S_num = Constant.S_Table[i][xnum][ynum];
			S += S_num/8;
			S += (S_num%8)/4;
			S += (S_num%4)/2;
			S += S_num%2;
		}
		
		Fstr = "";
		for(int i = 0; i < S.length(); i++){	//P���û�
			Fstr += S.charAt(Constant.P_Table[i]);
		}
		
		return Fstr;
	}
	
	@Override
	String Encryption(String Lmessage,String Rmessage,String []key){
		int number = this.getFN();
		String str = Lmessage + Rmessage;
		String Ls="", Rs="";
		for(int i = 0; i < str.length(); i++){	//��ʼ�û�
			if(i < str.length()/2)
				Ls += str.charAt(Constant.IP_Table[i]);
			else
				Rs += str.charAt(Constant.IP_Table[i]);
		}
		
		for(int i = 0; i < number; i++){	//16�ּ���
			String Fstr = F(Rs,key[i]);
			String xor = "";
			for(int j = 0; j < Ls.length(); j++){	//�󲿷����ֺ���������
				if(Ls.charAt(j) == Fstr.charAt(j))
					xor += "0";
				else
					xor += "1";
			}
			//����λ��
			Ls = Rs;
			Rs = xor;
			
		}
		
		str = Ls + Rs;
		Ls = "";
		Rs = "";
		for(int i = 0; i < str.length(); i++){	//���ʼ�û�
			if(i < str.length()/2)
				Ls += str.charAt(Constant.IP_1_Table[i]);
			else
				Rs += str.charAt(Constant.IP_1_Table[i]);
		}
		str = Ls+Rs;
		
		return str;
	}
	
	@Override
	String Decryption(String Lmessage,String Rmessage,String []key){
		int number = this.getFN();
		String str = Lmessage + Rmessage;
		String Ls="", Rs="";
		for(int i = 0; i < str.length(); i++){	//��ʼ�û�
			if(i < str.length()/2)
				Ls += str.charAt(Constant.IP_Table[i]);
			else
				Rs += str.charAt(Constant.IP_Table[i]);
		}
		
		for(int i = 0; i < number; i++){	//16�ֽ���
			
			String Fstr = F(Ls,key[i]);
			String xor = "";
			for(int j = 0; j < Rs.length(); j++){	//�Ҳ������ֺ���������
				if(Rs.charAt(j) == Fstr.charAt(j))
					xor += "0";
				else
					xor += "1";
			}
			//����λ��
			Rs = Ls;
			Ls = xor;
		}
		
		str = Ls + Rs;
		Ls = "";
		Rs = "";
		for(int i = 0; i < str.length(); i++){	//���ʼ�û�
			if(i < str.length()/2)
				Ls += str.charAt(Constant.IP_1_Table[i]);
			else
				Rs += str.charAt(Constant.IP_1_Table[i]);
		}
		str = Ls+Rs;
		
		return str;
	}
}
