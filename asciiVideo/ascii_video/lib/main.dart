import 'dart:io';
import 'dart:typed_data';

import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:image/image.dart' as imglib;

late List<CameraDescription> cameras;

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();

  cameras = await availableCameras();
  runApp(CameraApp());
}

class CameraApp extends StatefulWidget {
  @override
  _CameraAppState createState() => _CameraAppState();
}

class _CameraAppState extends State<CameraApp> {
  late CameraController controller;
  bool _isDetecting = false;
  late List<Plane> planes;
  int counter = 0;
  @override
  void initState() {
    super.initState();
    controller = CameraController(cameras[0], ResolutionPreset.max);
    controller.initialize().then((_) {
      if (!mounted) {
        return;
      }
      frames();
      setState(() {});
    });
  }

  @override
  void setState(VoidCallback fn) {}

  void frames() {
    counter++;
    controller.startImageStream((CameraImage image) {
      if (_isDetecting) return;
      _isDetecting = true;
      try {
        sleep(Duration(milliseconds: 16));
        planes = image.planes;
        print(planes[0].bytes);
      } catch (e) {
        // await handleExepction(e)
      } finally {
        _isDetecting = false;
      }
    });
    print("dupa");
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // double width = MediaQuery.of(context).size.width;
    // double height = MediaQuery.of(context).size.height;
    if (!controller.value.isInitialized) {
      return Container();
    }
    return MaterialApp(
      home: Container(
        child: Text(
          String.fromCharCode(counter),
        ),
        color: Colors.amber,
      ),
    );
  }
}
