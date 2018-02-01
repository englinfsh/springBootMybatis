package com.lin.feng.sheng.service.outer;

public interface IOrderService {

	public void order() throws Exception;
	public void noTxOrder() throws Exception;
	public void updateOrder() throws Exception;
}
