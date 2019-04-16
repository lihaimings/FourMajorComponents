# FourMajorComponents
主要是安卓四大组件的代码，其中service和BroadcastReceive主要显示代码的实现，并没有什么功能。contentProvider做了一个实例，通过获取手机的通讯录、短信、本地音乐并在listview中显示出来
#联系人 ：权限：READ_CONTACTS
#              uri:ContectsContract.CommonDataKinds.Phone.CONTENT_URI
 #                 表中的姓名列：ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
#                    表中的号码列:ContactsContract.CommonDataKinds.Phone.NUMBER
#短信:   权限：READ_SMS
#          uri:content://sms
 #         表中的号码:address
 #         表中的时间:date
  #        表中的内容:body
#音乐： 权限:READ_EXTERNAL_STORAGE
 #         MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
  #        表中的音乐名:Media.DISPLAY_NAME