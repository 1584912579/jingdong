package com.example.asus.jingdong.component;

import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.Details.ListDetailsActivity;
import com.example.asus.jingdong.ui.MakeSureOrder.AddNewAddrActivity;
import com.example.asus.jingdong.ui.MakeSureOrder.MakeSureOrderActivity;
import com.example.asus.jingdong.ui.OrderList.fragment.FragmentAllOrder;
import com.example.asus.jingdong.ui.OrderList.fragment.FragmentWaitOrder;
import com.example.asus.jingdong.ui.OrderList.fragment.Fragmentcanc;
import com.example.asus.jingdong.ui.OrderList.fragment.Fragmentpaid;
import com.example.asus.jingdong.ui.Search.SearchTouActivity;
import com.example.asus.jingdong.ui.ShopCart.ShopCartActivity;
import com.example.asus.jingdong.ui.SpLb.ListActivity;
import com.example.asus.jingdong.ui.dlassify.Fragmentdlassify;
import com.example.asus.jingdong.ui.homepage.FragmentHomePage;
import com.example.asus.jingdong.ui.login.LoginActivity;
import com.example.asus.jingdong.ui.mine.UserInfoActivity;
import com.example.asus.jingdong.ui.register.RegisterActivity;
import com.example.asus.jingdong.ui.shopping.Fragmentshopping;

import dagger.Component;

/**
 * Created by asus on 2018/5/10.
 * 链接
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(FragmentHomePage fragmentHomePage);

    void inject(Fragmentdlassify fragmentdlassify);
    void inject(ListActivity listActivity);
    void inject(ListDetailsActivity listDetailsActivity);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(SearchTouActivity searchTouActivity);
    void inject(Fragmentshopping fragmentshopping);
    void inject(ShopCartActivity shopCartActivity);
    void inject(MakeSureOrderActivity makeSureOrderActivity);
    void inject(AddNewAddrActivity addNewAddrActivity);
    void inject(UserInfoActivity userInfoActivity);
    void inject(FragmentAllOrder fragmentAllOrder);
    void inject(FragmentWaitOrder fragmentWaitOrder);
    void inject(Fragmentpaid fragmentpaid);
    void inject(Fragmentcanc fragmentcanc);
}
