Utils.java

	void copy(Context context,String text); 		//复制到剪贴板
	
	int  sp2px(Context context, float sp);  		//sp to px
	
	int  px2dip(Context context, float pxValue); 	//px to dip
	
	int  dip2px(Context context, float dp);			//dip to px
	
	void delete(String path);						//删除文件夹
	
	long calcAvailableMemery();						//计算可用的存储空间
	
	void setStatusBarColor(Activity context, int color);	//设置状态栏颜色
	
	int getStatusHeight(Context context);					//获取状态栏高度
	
	void showKeyboar(final Context context,final View view); //打开键盘
	
	void closeKeyboard(Context context, View view);			//关闭键盘
	
	String byteToString(byte[] data);						//byte to String
	
	boolean isValidPhoneNumber(String phoneNumber);			//是否为手机号码
	
	void installDownload(Context context, String path);		//安装apk
	
	
	
	
ThreeDesUtis.java	

	void setKeyiv(byte[] iv);			//设置iv向量值
	
	String Des3EncodeCBC(String arg1, String arg2);  //cbc加密:arg1是key，arg2是要加密的内容
	
	String DesDecodeCBC(String arg1, String arg2);   //cbc解密:arg1是key,arg2是密文
	
	//ECB的尚未封装
	

ShareUtils.java	

	shareIntent(Context context, String titleString, String content); 	//分享intent
	
	void intentMarket(Context context,String packageName);				//跳转到市场评价
	 
	 
NetUtils.java

	String  post(Context context,String urlString,Map<String,String > params);//post 请求
	
	String getMacAddress(Context context); 			//获取mac地址
	
	String getPhoneImei(Context context);			//获取手机imei
	
	boolean isNetworkConnected(Context context);	//网络是否连接
	
	boolean isStartWifi(Context context);			//是否开启了wifi
	 
	
	
	
	
	