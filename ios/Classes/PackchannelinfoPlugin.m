#import "PackchannelinfoPlugin.h"
#import <packchannelinfo/packchannelinfo-Swift.h>

@implementation PackchannelinfoPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPackchannelinfoPlugin registerWithRegistrar:registrar];
}
@end
