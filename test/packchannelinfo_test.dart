import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:packchannelinfo/packchannelinfo.dart';

void main() {
  const MethodChannel channel = MethodChannel('packchannelinfo');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await Packchannelinfo.platformVersion, '42');
  });
}
