# 얼굴을 탐지하고 모자 씌우기 

![output](./img/output.gif)



```c++
//
//  main.cpp
//  face-detection
//
//  Created by 김다빈 on 2019/12/09.
//  Copyright © 2019 김다빈. All rights reserved.
//

#include <opencv2/opencv.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/imgproc/imgproc.hpp>

#include <iostream>

using namespace std;
using namespace cv;

//VideoCapture vcap("/Users/kimdabin/db/workspace/opencv/face-detection/face-detection/face.MP4");


   Mat hatImg = imread("/Users/kimdabin/db/workspace/opencv/face-detection/face-detection/hat_img2.png");
   CascadeClassifier face_classifier("/Users/kimdabin/db/workspace/opencv/face-detection/face-detection/haarcascade_frontalface_default.xml");

CascadeClassifier eye_classifier("/Users/kimdabin/db/workspace/opencv/face-detection/face-detection/haarcascade_eye.xml");



int main(int argc, const char * argv[]) {
    // insert code here...
   
    VideoCapture vcap(0);
    VideoWriter writer;
    
    if(face_classifier.empty()||eye_classifier.empty()){
                  cerr<< "xml load failed" <<endl;
                        return -1;
              }
  
      
       
      
      //
       
     
       if(!vcap.isOpened()){
           return -1;
       }
       
       namedWindow("video",1);
    Size size = Size((int)vcap.get(CAP_PROP_FRAME_WIDTH),
    (int)vcap.get(CAP_PROP_FRAME_HEIGHT));
       writer.open("output.avi", VideoWriter::fourcc('M', 'J', 'P', 'G'), 30.0, size, true);
       
       while(1){//vcap.read(src)
           Mat src, gray, binary, hatGray, hatROI;
          vcap >> src;
           
         
        
           cvtColor(src, gray, COLOR_BGR2GRAY );
           equalizeHist(gray, gray);
           
        
           adaptiveThreshold(gray, binary, 255, ADAPTIVE_THRESH_GAUSSIAN_C, THRESH_BINARY, 21, 5);
           vector<Rect> faces;
           
           face_classifier.detectMultiScale(gray, faces);
   
                         
           Rect realFace(0,999999,0,0);
           for(Rect face: faces){
          
                vector<Rect> eyes;
            
             //  max=face.width<=max?max:face.width;
               
               if(realFace.width<=face.width&&realFace.height<=face.height){
                   realFace = face;
               }
               
                Mat faceROI = gray(realFace);
                Mat faceColor = src(realFace);
               
//                rectangle(src, realFace, Scalar(255,0,255), 2);
               Rect realEye(0,999999,0,999999);
               GaussianBlur(faceColor, faceColor, Size() , 10);
               faceColor.copyTo(src(Rect(realFace.x,realFace.y,realFace.width,realFace.height)));
               
             //  eye_classifier.detectMultiScale(faceROI, eyes);
               eye_classifier.detectMultiScale(faceROI, eyes, 1.1, 2, 0 |
              
               CASCADE_SCALE_IMAGE, Size(30, 30));

               
  
               cvtColor(hatImg, hatGray, COLOR_BGR2GRAY );
                            Mat mask(200-hatGray);
       
              
                for(Rect eye: eyes){
                  
            
                   // cout << eye;
                   
                  
                    if(realEye.y>=eye.y&&realEye.width<=eye.width){
                         realEye = eye;
                        
                    }
                    
     
                                
                            
                                      //Point center( (realEye.x+realEye.width) / 2, (realEye.y + realEye.height) / 2 );
                                                    
                                       //  circle(faceColor, center, realEye.width / 2 , Scalar(255, 0 , 0), 2, LINE_AA);
                                                   
                }
               
               
               if(realEye.width<faceROI.cols&&realEye.height<faceROI.rows){
                   int h = (faceROI.cols*hatImg.rows)/hatImg.cols;
                                             
                    Rect roi(realFace.x,abs((realFace.y)-h),faceROI.cols , h) ;
                    hatROI = src(roi);
                  // rectangle(src, roi, Scalar(255,255,255), 1);
                   resize(mask, mask,Size(faceROI.cols,h),INTER_NEAREST );
                   resize(hatImg, hatImg,Size(faceROI.cols,h),INTER_NEAREST );
                   hatImg.copyTo(hatROI,mask);
                                              
               }
               
                   
                                    
                }
               
            
               
       
           
           
            
            if( waitKey(33)==27){ //esc 키 누르면 종료
                return -1;
            }
            //hatImg.copyTo(hatROI,mask);
           writer.write(src);
           imshow("video", src);
           
       }
    
   
    return 0;
}






```

