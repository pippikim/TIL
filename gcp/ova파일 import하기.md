# ova 파일 import 하기



1. Storage 브라우저 버킷 생성 후 ova 파일 업로드

<img src="https://tva1.sinaimg.cn/large/007S8ZIlgy1gjgrwpn9tfj30ms0e4ab5.jpg" alt="image-20201007155332487" style="zoom:50%;" /> 

<img src="https://tva1.sinaimg.cn/large/007S8ZIlgy1gjgry5ml8ij32d20cgn08.jpg" alt="image-20201007155457872" style="zoom:50%;" />

<img src="https://tva1.sinaimg.cn/large/007S8ZIlgy1gjgrz76h3dj32d20owted.jpg" alt="image-20201007155557773" style="zoom:50%;" />







```
gcloud compute instances import hadoop01 --os=centos-7 --source-uri=gs://vanilla-stock/hadoop.ova --zone=asia-northeast1-a
```

```
gcloud compute instances import hadoop04 --os=centos-7 --source-uri=gs://vanilla-stock/hadoop04.ova --zone=asia-northeast2-a
```

![image-20201007213334555](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjh1qhf6mxj32d20owq8h.jpg)





