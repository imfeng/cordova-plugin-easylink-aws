//
//  CDVEasyLink.h
//  HelloCordova
//
//  Created by Thomas.Wang on 2016/11/14.
//
//

#import <Cordova/CDV.h>
#import "EasyLinkSDK/EasyLink/Common/EasyLink.h"//加easylink头文件

@interface CDVEasyLink : CDVPlugin
{
    EASYLINK *m_easylink_config;
    ELAsyncUdpSocket *udpSocket;
    CDVInvokedUrlCommand *m_command;
}
- (void)getWifiSSid:(CDVInvokedUrlCommand*)command;
- (void)startSearch:(CDVInvokedUrlCommand*)command;
- (void)stopSearch:(CDVInvokedUrlCommand*)command;
@end
