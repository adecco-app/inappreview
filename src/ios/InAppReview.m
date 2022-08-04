/********* InAppReview.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import "StoreKit/StoreKit.h"

@interface InAppReview : CDVPlugin
- (void)requestReview:(CDVInvokedUrlCommand*)command;
@end

@implementation InAppReview

- (void)requestReview:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    
    @try {
        if (@available(iOS 14.0, *)) { 
            [SKStoreReviewController requestReviewInScene:self.viewController.view.window.windowScene]; 
        } else if (@available(iOS 10.3, *)) { 
            [SKStoreReviewController requestReview]; 
        }
        
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    } @catch (id anException) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
