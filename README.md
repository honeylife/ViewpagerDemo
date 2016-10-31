# ViewpagerDemo项目简介

  ＃ 一、App启动时欢迎页的倒计时
  
   主要的实现思路：
    Handler机制，发送消息，自己处理消息更新UI。
    ```java
      @Override
    public void run() {
        try {
            while (flag) {
                handler.sendEmptyMessage(0);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
   ```
   而在Handler的handleMessage()方法中处理UI的数据显示，而布局文件比较简单就帖代码了。
   ```java
   private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTime--;
            if (mTime > 0) {
                welcome_tv.setText(mTime + "秒跳转");
            } else if (mTime == 0) {
                flag = false;
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                flag = false;
            }
        }
    };
   ```
  # 二、轮播图的实现
  采用Handler机制实现没个3秒换图，发送消息
  初始化小圆点的关键代码：
  采用Handler机制实现没个3秒换图，发送消息
  ```java
  // 初始化图片资源
        imageList = new ArrayList<ImageView>();
        for (int i : images) {
            // 初始化图片资源
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(i);
            imageList.add(imageView);

            // 添加指示小点
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,
                    15);
            params.rightMargin = 20;
            params.bottomMargin = 10;
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.white);
            if (i == R.drawable.a) {
                //默认聚焦在第一张
                point.setBackgroundResource(R.drawable.back);
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }

            point_group.addView(point);
        }
  ```
  而 设置当前viewPager的位置
  ```java
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imageList.size()));
  ```
  接收到消息处理viewpager的图片切换
  ```java
  private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 执行滑动到下一个页面
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                // 在发一个handler延时
                handler.sendEmptyMessageDelayed(0, 5000);
            }
        }
    };
  ```
