import 'dart:async';

import 'package:flutter/services.dart';

class Packchannelinfo {
  static const MethodChannel _channel =
      const MethodChannel('packchannelinfo');

  static Future<String> get getchannelinfo async {
    final String version = await _channel.invokeMethod('getchannelinfo');
    return version;
  }
}
