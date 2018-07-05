package com.thomas.easylink_tes;
import android.content.Context;
/** OLD
import com.mxchip.easylink.EasyLinkAPI;
import com.mxchip.wifiman.EasyLinkWifiManager;
*/
/** 0.2.6 */
import io.fogcloud.sdk.easylink.api.EasyLink;
import io.fogcloud.sdk.easylink.api.EasylinkP2P;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.easylink.helper.EasyLinkParams;
/** */
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Thomas.Wang on 16/11/11.
 */
public class EasyLink_tes extends CordovaPlugin{
    /** OLD
    private EasyLinkAPI elapi;
    private EasyLinkWifiManager mWifiManager = null;
    private UDPServer server;
    */

    private Context ctx = null;
    /** 0.2.6 */
    private EasyLink el;
    private EasylinkP2P elp2p;
    /** */

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        ctx = cordova.getActivity();
        /** OLD
        elapi = new EasyLinkAPI(ctx);
        server = new UDPServer();
        */
        el = new EasyLink(ctx);
        elp2p = new EasylinkP2P(ctx);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        //获取wifiSSid
        if (action.equals("getWifiSSid")){
            /** OLD
            mWifiManager = new EasyLinkWifiManager(ctx);
            callbackContext.success(mWifiManager.getCurrentSSID());
            */
            callbackContext.success(el.getSSID());
            return true;
        }

        //开始搜索
        if (action.equals("startSearch")) {
            startSearch(args,callbackContext);
            return true;
        }

        //停止搜索
        if(action.equals("stopSearch")){
            elp2p.stopEasyLink(new EasyLinkCallBack() {
                @Override
                public void onSuccess(int code, String message) {
                    callbackContext.success(message);
                }
                @Override
                public void onFailure(int code, String message) {
                    callbackContext.error(message);
                }
            });
            /** OLD
            elapi.stopEasyLink();
            server.setLife(false);
            callbackContext.success("停止配网");
            */
            return true;
        }
        return false;
    }

    private void startSearch(JSONArray args,CallbackContext callbackContext) throws JSONException{
        /** OLD */
        String wifiSSid = args.getString(0).trim();
        String wifiPsw = args.getString(1).trim();

        if (wifiSSid==null||wifiSSid.isEmpty()){
            callbackContext.error("NO SSID");

        }else if (wifiPsw==null||wifiPsw.isEmpty()) {
            callbackContext.error("NO PASSWORD");
        } else {
            /** 0.2.6 */
            EasyLinkParams elp = new EasyLinkParams();
            elp.ssid = wifiSSid;
            elp.password = wifiPsw;
            elp.sleeptime = 50;
            elp.runSecond = 50;
            elp2p.startEasyLink(elp, new EasyLinkCallBack() {
                @Override
                public void onSuccess(int code, String message) {
                    callbackContext.success(message);
                }

                @Override
                public void onFailure(int code, String message) {
                    callbackContext.error(message);
                }
            });
            /** */
            /** OLD
            elapi.startEasyLink(ctx, wifiSSid,wifiPsw);
            // 开启UDP服务器
            ExecutorService exec = Executors.newCachedThreadPool();
            server.setCallbackContextAndEasyLink(callbackContext,elapi);
            server.setLife(true);
            exec.execute(server);
            //callbackContext.success("开始配网");
            */
        }
    }

}
