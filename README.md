# Game-Testing on AWS Device Farm

Sample of using OpenCV3's AKAZE local features matching to run image recognition 
on AWS Device Farm for game testing.

**Reference:**

- [AWS Device Farm with Appium Sample](https://github.com/awslabs/aws-device-farm-appium-tests-for-sample-app)
- [TestDroid Image Recognition Sample](https://github.com/saadchdhry/testdroid-samples/tree/master/appium/sample-scripts/java-image-recognition/bug_invaders)

----------

**Demo Result on AWS Device Farm:**
![aws_result](https://raw.github.com/yingkai-liao/awsdf-game-testing/master/demo/aws_result.png)

Dependencies
=======================================

- Java JDK 7 or newer
- Apache Maven
- OpenCV 3.0.0 (compiled java libs can be found at project_root/opencv-3.0.0)

Build OpenCV Libraries (if want/need)
=======================================

To build OpenCV3.0.0 on CentOS7:

Install Dependencies:
```
yum groupinstall "Development Tools"
yum install epel-release
yum install cmake
yum install java-1.7.0-openjdk-devel.x86_64 
yum install maven
yum install ant
```
Build OpenCV:  
```
wget https://github.com/Itseez/opencv/archive/3.0.0.zip
unzip 3.0.0.zip
cd opencv-3.0.0
mkdir build
cd build
cmake -DBUILD_SHARED_LIBS=OFF  -DENABLE_PRECOMPILED_HEADERS=OFF ..
```
After cmake done, check tow points:
1. java in OpenCV modules to be build list.
2. PNG librariy is build from OpenCV's srouce code.
![check_cmake](https://raw.github.com/yingkai-liao/awsdf-game-testing/master/demo/opencv_install.png)

then run make command
```
make -j8
```
after done, copy libraries from /lib and /bin.

Appium Setup
=============
appium setting is in AppiumTestBase.java

set `static final testPlatform target = ` to change target platform
(`testPlatform.Localhost` or `testPlatform.Aws`).

Run on AWS Device Farm
==============
1. Run `mvn clean package -DskipTests=true` at project_root
2. upload .apk file and project_root/target/zip-with-dependencies.zip to AWS Device Farm
