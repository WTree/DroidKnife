Utils.java

	void copy(Context context,String text); 		//���Ƶ�������
	
	int  sp2px(Context context, float sp);  		//sp to px
	
	int  px2dip(Context context, float pxValue); 	//px to dip
	
	int  dip2px(Context context, float dp);			//dip to px
	
	void delete(String path);						//ɾ���ļ���
	
	long calcAvailableMemery();						//������õĴ洢�ռ�
	
	void setStatusBarColor(Activity context, int color);	//����״̬����ɫ
	
	int getStatusHeight(Context context);					//��ȡ״̬���߶�
	
	void showKeyboar(final Context context,final View view); //�򿪼���
	
	void closeKeyboard(Context context, View view);			//�رռ���
	
	String byteToString(byte[] data);						//byte to String
	
	boolean isValidPhoneNumber(String phoneNumber);			//�Ƿ�Ϊ�ֻ�����
	
	void installDownload(Context context, String path);		//��װapk
	
	
	
	
ThreeDesUtis.java	

	void setKeyiv(byte[] iv);			//����iv����ֵ
	
	String Des3EncodeCBC(String arg1, String arg2);  //cbc����:arg1��key��arg2��Ҫ���ܵ�����
	
	String DesDecodeCBC(String arg1, String arg2);   //cbc����:arg1��key,arg2������
	
	//ECB����δ��װ
	

ShareUtils.java	

	shareIntent(Context context, String titleString, String content); 	//����intent
	
	void intentMarket(Context context,String packageName);				//��ת���г�����
	 
	 
NetUtils.java

	String  post(Context context,String urlString,Map<String,String > params);//post ����
	
	String getMacAddress(Context context); 			//��ȡmac��ַ
	
	String getPhoneImei(Context context);			//��ȡ�ֻ�imei
	
	boolean isNetworkConnected(Context context);	//�����Ƿ�����
	
	boolean isStartWifi(Context context);			//�Ƿ�����wifi
	 
	
	
	
	
	