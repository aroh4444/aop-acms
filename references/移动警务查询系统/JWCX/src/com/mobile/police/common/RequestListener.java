package com.mobile.police.common;

/**
 * �Զ���һ���������ӿ�
 * �Ժ����м������඼Ҫʵ�ָýӿ�
 * @author Administrator
 *
 */
public interface RequestListener {
	
	//完成时，需要完成的动作
	public void onComplete(String result); 
	
	//异常时，需要完成的动作
	public void onException(Exception e);

}
