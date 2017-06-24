package com.mega4tech.oction.mvp.upcoming.auctions.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mega4tech.oction.Application;
import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.model.repository.Repository;
import com.mega4tech.oction.mvp.upcoming.auctions.interactor.UpcomingAuctionsInteractor;
import com.mega4tech.oction.mvp.upcoming.auctions.presenter.impl.UpcomingAuctionsPresenterImpl;
import com.mega4tech.oction.mvp.upcoming.auctions.view.UpcomingAuctionsView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aboodba on 17/06/2017.
 */
public class UpcomingAuctionsPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    UpcomingAuctionsInteractor interactor;
    @Mock
    UpcomingAuctionsView view;
    @Mock
    Repository repository;
    @Mock
    Application application;
    @InjectMocks
    UpcomingAuctionsPresenterImpl presenter;


    @Before
    public void setUp() throws Exception {

    }
    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }


    @Test
    public void getAuctionsWhenOnline() throws Exception {
        presenter.onViewAttached(view);
        Gson gson = new Gson();
        List<Auction> auctions = gson.fromJson(json, new TypeToken<List<Auction>>(){}.getType());
        when(interactor.getAuctions()).thenReturn(Observable.just(auctions));
        when(interactor.isOnline()).thenReturn(true);
        presenter.getAuctions();
        verify(view, times(1)).reload();
        verify(view, times(1)).display(auctions);

    }

    @Test
    public void getAuctionsWhenOnlineButExceptionHappened() throws Exception {
        presenter.onViewAttached(view);
        Gson gson = new Gson();
        List<Auction> auctions = gson.fromJson(json, new TypeToken<List<Auction>>(){}.getType());
        when(interactor.getAuctions()).thenReturn(Observable.error(new Throwable("test error")));
        when(interactor.isOnline()).thenReturn(true);
        presenter.getAuctions();
        verify(view, times(1)).showError("test error");
        verify(view, times(0)).display(any());
        verify(view, times(1)).reload();

    }


    @Test
    public void getAuctionsWhenOffline() throws Exception {
        presenter.onViewAttached(view);
        when(interactor.isOnline()).thenReturn(false);
        presenter.getAuctions();
        verify(view, times(1)).showOffline();
        verify(view, times(0)).reload();
        verify(view, times(0)).display(any());
    }



    private String
            json = "[{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"Samsung Galaxy S7 Edge Gold 32GB\",\"description\":\"Galaxy S7 \\u70ba\\u667a\\u80fd\\u624b\\u6a5f\\u91cd\\u65b0\\u5b9a\\u7fa9\\uff0c\\u900f\\u904e\\u8d85\\u8d8a\\u624b\\u6a5f\\u65e2\\u6709\\u4f7f\\u7528\\u754c\\u9650\\uff0c\\u6211\\u5011\\u6b63\\u5fb9\\u5e95\\u6539\\u8b8a\\u60a8\\u7684\\u5206\\u4eab\\u9ad4\\u9a57\\u548c\\u56de\\u61b6\\u7684\\u65b9\\u5f0f\\uff0c\\u9019\\u5c07\\u6703\\u662f\\u4e00\\u6b21\\u767c\\u751f\\u5728\\u624b\\u6a5f\\u4e0a\\u7684\\u91cd\\u5927\\u7a81\\u7834\\uff0c\\u6539\\u8b8a\\u53f2\\u7121\\u524d\\u4f8b\\u3002\\nGalaxy S7 It's not just a new phone. It brings a new way of thinking about what a phone can do. You defined the possibilities and we redefined the phone. The Galaxy S7 and S7 edge. Rethink what a phone can do.\",\"productPrice\":\"5398\",\"product_id\":\"27\",\"productCurrency\":\"HKD\",\"auctionId\":\"149\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"c46e66ef6c6b88abc858fd55116f39ed\",\"start_time\":\"2017-06-19 04:00:00\",\"start_time_unix\":\"1497844800\",\"end_time\":\"2017-06-25 04:00:00\",\"end_time_unix\":\"1498363200\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"15\",\"media\":\"\\/media\\/0_wIK5qulXASZ0R5ZowH7vtN6hMHPmKJvBvYqbDYFd.jpeg\",\"title\":\"\",\"product_id\":\"27\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"UA Cinema $1000 Voucher\\nUA Cinema $1,000 \\u79ae\\u5238\",\"description\":\"UA Cinema $1000 Voucher\\nUA $1000 \\u5f71\\u9662\\u79ae\\u5238\",\"productPrice\":\"1000\",\"product_id\":\"28\",\"productCurrency\":\"HKD\",\"auctionId\":\"150\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"34f62d523c171c044b0922d3b27d8b37\",\"start_time\":\"2017-06-19 07:00:00\",\"start_time_unix\":\"1497855600\",\"end_time\":\"2017-06-26 07:00:00\",\"end_time_unix\":\"1498460400\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"46\",\"media\":\"\\/media\\/0_QYJuHG47ow9ADvnzIDZLRPJTSIfMr4HDOlznLXwJ.jpeg\",\"title\":\"\",\"product_id\":\"28\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"Beats PowerBeats3\",\"description\":\"Powerbeats3 Wireless \\u8033\\u6a5f\\u52a9\\u4f60\\u5728\\u9ad4\\u80fd\\u8a13\\u7df4\\u4e2d\\u7a81\\u7834\\u81ea\\u5df1\\u3002\\u9577\\u9054 12 \\u5c0f\\u6642\\u96fb\\u6c60\\u4f7f\\u7528\\u6642\\u9593\\uff0c\\u8db3\\u5920\\u914d\\u5408\\u4f60\\u591a\\u6b21\\u9ad4\\u80fd\\u8a13\\u7df4\\u3002\\u7a69\\u56fa\\u8cbc\\u5408\\u7684\\u8033\\u639b\\uff0c\\u63d0\\u4f9b\\u6700\\u8212\\u9069\\u7a69\\u59a5\\u7684\\u9ad4\\u9a57\\u3002\\u6709\\u4e86 Fast Fuel \\u6280\\u8853\\uff0c\\u53ea\\u9700\\u5145\\u96fb 5 \\u5206\\u9418\\uff0c\\u5373\\u53ef\\u64ad\\u653e\\u97f3\\u6a02 1 \\u5c0f\\u6642\\u3002\\u6297\\u6c57\\u3001\\u9632\\u6ffa\\u8010\\u6c34\\u8a2d\\u8a08\\u4ee4\\u9019\\u6b3e\\u8033\\u6a5f\\u66f4\\u70ba\\u8010\\u7528\\uff0c\\u800c\\u96d9\\u9a45\\u52d5\\u97f3\\u97ff\\u6280\\u8853\\u5247\\u70ba\\u8072\\u97f3\\u6ce8\\u5165\\u52d5\\u611f\\uff0c\\u8b93\\u4f60\\u9032\\u884c\\u9ad4\\u80fd\\u8a13\\u7df4\\u6642\\u6e3e\\u8eab\\u662f\\u52c1\\u3002\\nTake your workout to the next level with Powerbeats3 Wireless earphones, featuring up to 12 hours of battery life to last through multiple workouts and secure-fit earhooks to maximize comfort and stability. With Fast Fuel, a 5-minute charge gives you 1 hour of playback. Sweat and water resistance provides trusted durability and dual-driver acoustics deliver dynamic sound to power your workout.\",\"productPrice\":\"1488\",\"product_id\":\"29\",\"productCurrency\":\"HKD\",\"auctionId\":\"151\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"18c567bef4b71dd88bf61472adc82755\",\"start_time\":\"2017-06-19 10:00:00\",\"start_time_unix\":\"1497866400\",\"end_time\":\"2017-06-26 10:00:00\",\"end_time_unix\":\"1498471200\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"27\",\"media\":\"\\/media\\/0_30P2jbuf0pEIfMv4xOwlKQ8zXor9wCPgnM8g7fw2.jpeg\",\"title\":\"\",\"product_id\":\"29\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"X-mini SUPA\",\"description\":\"\\u7121\\u7dda\\u63da\\u8072\\u5668\\u5c08\\u70ba\\u4fbf\\u651c\\u6027\\u800c\\u8a2d\\u8a08\\uff0c\\u9748\\u611f\\u4f86\\u81ea\\u5f37\\u5927\\u7684\\u8001\\u5f0f\\u81ea\\u52d5\\u9ede\\u5531\\u6a5f\\u7cfb\\u7d71\\uff0c\\u9ad4\\u73fe\\u4e86\\u5167\\u7f6eDSP\\u548cDPAC\\u97f3\\u983b\\u6280\\u8853\\u7684\\u6e05\\u6670\\u97f3\\u8cea\\u3002 \\u4e00\\u500b\\u55ae\\u4e00\\u7684\\u65cb\\u8f49\\u64a5\\u76e4\\u4f86\\u63a7\\u5236\\u60a8\\u7684\\u97f3\\u6a02\\u548c\\u97f3\\u91cf\\uff0cX-mini\\u2122SUPA\\u53ef\\u4ee5\\u8b93\\u60a8\\u4e00\\u9375\\u63a7\\u5236\\u60a8\\u7684\\u97f3\\u6a02\\u3002\\nA wireless speaker designed for portability and inspired by powerful vintage jukebox systems, experience crystal clear sound with built-in DSP and DPAC audio technologies. A single swivel dial to control your music and volume, the X-mini\\u2122 SUPA empowers you to control your music in one touch.\",\"productPrice\":\"1380\",\"product_id\":\"30\",\"productCurrency\":\"HKD\",\"auctionId\":\"152\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"f2d089d19ac06ecb755227c1b35ddc9e\",\"start_time\":\"2017-06-19 13:00:00\",\"start_time_unix\":\"1497877200\",\"end_time\":\"2017-06-26 13:00:00\",\"end_time_unix\":\"1498482000\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"22\",\"media\":\"\\/media\\/0_v5twhYiXRspF6rsTtX6kNR5Yaji6KQEqKWeNE36u.jpeg\",\"title\":\"\",\"product_id\":\"30\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"PS4 Pro + VR\",\"description\":\"PlayStation\\u00aeVR\\u662f\\u63d0\\u5347PlayStation\\u00ae4\\u9b45\\u529b\\uff0c\\u8c50\\u5bcc\\u904a\\u6232\\u9ad4\\u9a57\\u7684\\u865b\\u64ec\\u73fe\\u5be6(Virtual Reality\\/VR)\\u786c\\u4ef6\\u88dd\\u7f6e\\u3002\\u6234\\u4e0aVR\\u982d\\u6234\\u88dd\\u7f6e\\u5f8c\\uff0c\\u5177\\u903c\\u529b\\u76843D\\u7a7a\\u9593\\u5c07\\u5168\\u65b9\\u4f4d360\\u5ea6\\u570d\\u7e5e\\u73a9\\u5bb6\\u3002\\u914d\\u5408\\u7368\\u81ea\\u958b\\u767c\\u76843D\\u97f3\\u6548\\u6280\\u8853\\u5275\\u9020\\u51fa\\u9707\\u64bc\\u81e8\\u5834\\u611f\\uff0c\\u4ee4\\u73a9\\u5bb6\\u7336\\u5982\\u9032\\u5165\\u4e86\\u904a\\u6232\\u4e16\\u754c\\u3002\\nPlayStation\\u00aeVR is the latest member of the PS4\\u2122 family. Immerse yourself in extraordinary new worlds, put yourself at the centre of an incredible gaming universe and experience a new way to play with PlayStation\\u00aeVR. Whichever way you turn, the 360 degree view with 3D audio makes you part of a living, breathing world with a seamless field of view.\",\"productPrice\":\"6999\",\"product_id\":\"31\",\"productCurrency\":\"HKD\",\"auctionId\":\"153\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"e693e0e5ad86edb7971a163aa1544300\",\"start_time\":\"2017-06-20 04:00:00\",\"start_time_unix\":\"1497931200\",\"end_time\":\"2017-06-27 04:00:00\",\"end_time_unix\":\"1498536000\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"16\",\"media\":\"\\/media\\/0_cHV9CKZYwni8Y3a8JURgnrVqE4HfXdSSbyUHRyg9.jpeg\",\"title\":\"\",\"product_id\":\"31\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"Starbucks $1000 Voucher \\u661f\\u5df4\\u514b\\u79ae\\u5238$1,000\",\"description\":\"\\u661f\\u5df4\\u514b\\u79ae\\u5238 HKD$1,000\\nStarbucks HKD$1,000 Voucher \",\"productPrice\":\"1000\",\"product_id\":\"32\",\"productCurrency\":\"HKD\",\"auctionId\":\"154\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"e1e461344540614adf3ae450264f1784\",\"start_time\":\"2017-06-20 07:00:00\",\"start_time_unix\":\"1497942000\",\"end_time\":\"2017-06-24 07:00:00\",\"end_time_unix\":\"1498287600\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"28\",\"media\":\"\\/media\\/0_Aqs9VuyE8ye8NaruEm7hVpqq8CUsGAZTw3pzRMPJ.jpeg\",\"title\":\"\",\"product_id\":\"32\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"1MORE E1010\",\"description\":\"1MORE \\u56db\\u55ae\\u5143\\u5708\\u9435\\u8033\\u6a5f\\u65bc2017\\u5e741\\u6708\\u6b63\\u5f0f\\u65bcCES\\u767c\\u4f48\\u30021MORE \\u81ea\\u4e3b\\u7814\\u767c\\u7684\\u632f\\u819c\\u9ed1\\u79d1\\u6280 \\\"DLC\\u985e\\u947d\\u78b3 \\u52d5\\u5708\\u55ae\\u5143+\\u52d5\\u9435\\u4e09\\u55ae\\u5143\\\"\\u8072\\u5b78\\u7d50\\u69cb\\u8a2d\\u8a08\\uff0c\\u845b\\u840a\\u7f8e\\u97f3\\u6a02\\u5927\\u5e2bLUCA\\u5b9a\\u8abf\\uff1b\\u98db\\u6a5f\\u5f15\\u64ce\\u6d41\\u7dda\\u578b\\uff0c\\u8a2d\\u8a08\\u8207\\u96d9\\u8272\\u967d\\u6975\\u5de5\\u85dd\\u5448\\u73fe\\u672a\\u4f86\\u79d1\\u6280\\u611f\\u3002\\u5b83\\u9a5a\\u8c54\\u7684\\u97f3\\u8cea\\u66f4\\u8b93CES\\u7684\\u5a92\\u9ad4\\u5011\\u9a5a\\u5606\\u4e0d\\u5df2\\uff01\\nWARNING! You may need to be seated when you first experience our 1MORE Quad Driver In-Ear Headphones. They create an audio experience unlike anything you've ever heard before. The sonic expression of your songs is so detailed and intimate you feel as if you are right there with your favorite artist as a fellow band member. We achieve this through serene clarity, acoustic perfection, and seamless ergonomics. Sit down, slip them in, and become the music. It's your music. You deserve it.\",\"productPrice\":\"1699\",\"product_id\":\"33\",\"productCurrency\":\"HKD\",\"auctionId\":\"155\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"bcd25ef01b64670a657fdacb4d9b172b\",\"start_time\":\"2017-06-20 10:00:00\",\"start_time_unix\":\"1497952800\",\"end_time\":\"2017-06-24 10:00:00\",\"end_time_unix\":\"1498298400\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"17\",\"media\":\"\\/media\\/0_imz5HWH3rbGl2Wgy6uX4sdfd3kLPsosSf3juxE8F.jpeg\",\"title\":\"\",\"product_id\":\"33\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"iPad Mini 128GB (Gold)\",\"description\":\"\\u5916\\u5f62\\u96d6\\u7136\\u5c0f\\u5de7\\uff0c\\u4f46\\u7d55\\u5c0d\\u4e0d\\u5bb9\\u5c0f\\u770b\\u3002iPad mini 4 \\u5167\\u85cf\\u7121\\u8207\\u502b\\u6bd4\\u7684\\u6548\\u80fd\\u548c\\u6f5b\\u80fd\\uff0c\\u5168\\u90e8\\u8b93\\u4f60\\u4e00\\u624b\\u638c\\u63e1\\u3002\\u800c\\u4e14\\uff0c\\u5b83\\u6bd4\\u4ee5\\u5f80\\u66f4\\u7e96\\u8584\\u3001\\u66f4\\u8f15\\u5de7\\uff0c\\u4f46\\u4f9d\\u7136\\u5f37\\u5927\\uff0c\\u8db3\\u4ee5\\u52a9\\u4f60\\u628a\\u7a2e\\u7a2e\\u5275\\u610f\\u69cb\\u601d\\u5e36\\u5230\\u66f4\\u9ad8\\u3001\\u66f4\\u9060\\u3002\\nThere\\u2019s more to mini than meets the eye. iPad mini 4 puts uncompromising performance and potential in your hand. It\\u2019s thinner and lighter than ever before, yet powerful enough to help you take your ideas even further.\",\"productPrice\":\"3250\",\"product_id\":\"34\",\"productCurrency\":\"HKD\",\"auctionId\":\"156\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"a1d556964274b5b86f07c8f5fcc25be4\",\"start_time\":\"2017-06-20 13:00:00\",\"start_time_unix\":\"1497963600\",\"end_time\":\"2017-06-29 13:00:00\",\"end_time_unix\":\"1498741200\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"30\",\"media\":\"\\/media\\/0_LFVS6R3HvpGCwT35wcCMxYqD16LriJi61tvrVCEQ.jpeg\",\"title\":\"\",\"product_id\":\"34\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"Nintendo Switch\",\"description\":\"\\u4efb\\u5929\\u5802\\u7684\\u65b0\\u4e00\\u4ee3\\u96fb\\u5b50\\u904a\\u6232\\u6a5f\\uff0c\\u64c1\\u6709\\u53ef\\u62c6\\u5378\\u63a7\\u5236\\u5668\\u548c\\u53ef\\u5206\\u96e2\\u5f0f\\u4e3b\\u6a5f\\uff0c\\u904a\\u6232\\u8f09\\u9ad4\\u6539\\u70ba\\u4f7f\\u7528\\u5361\\u5323\\uff0c\\u4efb\\u5929\\u5802Switch\\u7684\\u8655\\u7406\\u5668\\u4f7f\\u7528\\u4e86Nvidia\\u5c08\\u9580\\u5ba2\\u88fd\\u7684Tegra\\u7cfb\\u5217\\u8655\\u7406\\u5668\\uff0c\\u9019\\u662f\\u4efb\\u5929\\u5802\\u9996\\u6b21\\u63a1\\u7528Nvidia\\u7684\\u8655\\u7406\\u5668\\u3002\\nNintendo Switch is designed to go wherever you do, transforming from home console to portable system in a snap. So you get more time to play the games you love, however you like.\",\"productPrice\":\"2899\",\"product_id\":\"16\",\"productCurrency\":\"HKD\",\"auctionId\":\"157\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"c049392ecf996cc1fcc13a61e83c73bd\",\"start_time\":\"2017-06-21 04:00:00\",\"start_time_unix\":\"1498017600\",\"end_time\":\"2017-06-27 04:00:00\",\"end_time_unix\":\"1498536000\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"1\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"10\",\"media\":\"\\/media\\/0_JmtYh1qh9RKdmo7vtwdLq1LbwQe7fG49Y7ojbt9H.jpeg\",\"title\":\"\",\"product_id\":\"16\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"IKEA $1000 Voucher \\u5b9c\\u5bb6\\u5bb6\\u5c45 $1,000 \\u79ae\\u5238\",\"description\":\"IKEA $1000 Voucher\\n\\u5b9c\\u5bb6\\u5bb6\\u5c45 $1,000 \\u79ae\\u5238\",\"productPrice\":\"1000\",\"product_id\":\"35\",\"productCurrency\":\"HKD\",\"auctionId\":\"159\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"ef4869a17fff6607e848d395de4f66b1\",\"start_time\":\"2017-06-21 07:00:00\",\"start_time_unix\":\"1498028400\",\"end_time\":\"2017-06-30 07:00:00\",\"end_time_unix\":\"1498806000\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"23\",\"media\":\"\\/media\\/0_rSFsy7sb3ZrjxhYeI4wpP2KmGiKJk7aLF43wRWk0.jpeg\",\"title\":\"\",\"product_id\":\"35\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"Beats Pill\",\"description\":\"Beats Pill \\u529f\\u80fd\\u5f37\\u5927\\uff0c\\u4e0d\\u4f46\\u8b93\\u4f60\\u96a8\\u6642\\u96a8\\u5730\\u64ad\\u653e\\u9707\\u64bc\\u52d5\\u4eba\\u7684\\u97f3\\u8cea\\uff0c\\u66f4\\u53ef\\u8b93\\u6e3e\\u539a\\u7684\\u8072\\u5834\\u904d\\u6574\\u500b\\u623f\\u9593\\u3002Beats Pill \\u7684\\u4ecb\\u9762\\u7c21\\u7d04\\u6d41\\u9e97\\uff0c\\u76f4\\u89ba\\u6613\\u7528\\uff0c\\u66f4\\u5099\\u6709\\u5f15\\u4eba\\u5165\\u52dd\\u7684\\u529f\\u80fd\\uff0c\\u5e36\\u4f86\\u7368\\u7279\\u7684\\u5171\\u4eab\\u8046\\u807d\\u9ad4\\u9a57\\uff0c\\u5c07\\u4eba\\u8207\\u4eba\\u4e4b\\u9593\\u7684\\u8ddd\\u96e2\\u62c9\\u8fd1\\u3002\\nBeats Pill is designed to go wherever you do and fill the room with a rich clear sound field that has as much power as it does definition. With a sleek interface, the Beats Pill is intuitive to use and brings people together with engaging features for a unique shared listening experience.\",\"productPrice\":\"1888\",\"product_id\":\"36\",\"productCurrency\":\"HKD\",\"auctionId\":\"160\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"d08ce9bc6d60f6d0a013f684ce2636ab\",\"start_time\":\"2017-06-21 10:00:00\",\"start_time_unix\":\"1498039200\",\"end_time\":\"2017-06-27 10:00:00\",\"end_time_unix\":\"1498557600\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"52\",\"media\":\"\\/media\\/0_H1P5KtzXPnxf3PR1bnMmGMq92vHGSQq5qSgvdCU9.png\",\"title\":\"\",\"product_id\":\"36\"}]},{\"auction\":{\"user_name\":null,\"name\":null,\"profileImage\":null,\"userId\":null,\"title\":\"iPhone 7 256GB (Rose Gold)\",\"description\":\"iPhone 7 \\u5c07 iPhone \\u9ad4\\u9a57\\u4e2d\\u5404\\u500b\\u6700\\u91cd\\u8981\\u7684\\u74b0\\u7bc0\\uff0c\\u5927\\u5927\\u63d0\\u5347\\u3002\\u5b83\\u5e36\\u4f86\\u4e86\\u5148\\u9032\\u7684\\u5168\\u65b0\\u93e1\\u982d\\u7cfb\\u7d71\\uff0ciPhone \\u6b77\\u4f86\\u6700\\u5f37\\u7684\\u6548\\u80fd\\u8868\\u73fe\\u4ee5\\u53ca\\u6700\\u6301\\u4e45\\u7684\\u96fb\\u6c60\\u4f7f\\u7528\\u6642\\u9593\\uff1b\\u66f4\\u914d\\u5099\\u8b93\\u4f60\\u8eab\\u6b77\\u5176\\u5883\\u7684\\u7acb\\u9ad4\\u8072\\u63da\\u8072\\u5668\\uff0c\\u6700\\u4eae\\u9e97\\u3001\\u8272\\u5f69\\u6700\\u8c50\\u5bcc\\u7684 iPhone \\u986f\\u793a\\u5668\\uff0c\\u800c\\u4e14\\u9632\\u6ffa\\u8010\\u6c34\\u3002\\u5916\\u5728\\u5167\\u5728\\u7684\\u6bcf\\u5206\\u6bcf\\u5bf8\\u90fd\\u4e00\\u6a23\\u5f37\\u5927\\u3002\\u9019\\uff0c\\u5c31\\u662f iPhone 7\\u3002\\niPhone 7 dramatically improves the most important aspects of the iPhone experience. It introduces advanced new camera systems. The best performance and battery life ever in an iPhone. Immersive stereo speakers. The brightest, most colorful iPhone display. Splash and water resistance.And it looks every bit as powerful as it is. This is iPhone 7.\",\"productPrice\":\"7188\",\"product_id\":\"37\",\"productCurrency\":\"HKD\",\"auctionId\":\"161\",\"startingPrice\":\"0.00\",\"currency\":\"HKD\",\"price\":\"0.01\",\"unique_id\":\"5fb70f14996ec3a134f7637927f640a5\",\"start_time\":\"2017-06-21 13:00:00\",\"start_time_unix\":\"1498050000\",\"end_time\":\"2017-06-29 13:00:00\",\"end_time_unix\":\"1498741200\",\"minimumPrice\":\"4\",\"bidCount\":\"0\",\"auctionsHeldCount\":\"0\",\"bidderCount\":\"0\"},\"media\":[{\"id\":\"25\",\"media\":\"\\/media\\/0_B4HDGSTDjswvvylUtWeoxIazHAJrH0d8pVi9GD4O.jpeg\",\"title\":\"\",\"product_id\":\"37\"}]}]";


}