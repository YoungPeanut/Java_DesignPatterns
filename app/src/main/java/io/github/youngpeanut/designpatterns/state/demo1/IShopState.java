package io.github.youngpeanut.designpatterns.state.demo1;

/**
 *  A state interface
 *  �û������Ϲ����״̬�仯:
 *  ѡ����Ʒ --> ��ɶ��� --> ����ȡ��
 */
public interface IShopState {
    public void shop();
    public void generateBill();
    public void pay();
    //protected void changeState(ShopContext c, IShopState);
}