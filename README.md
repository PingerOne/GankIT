# GankIT
> 个人开源项目，基于 MVP + Retrofit + RxJava 进行架构，主要包括视频，干货，新闻，福利这四个大模块。
>
> 个人博客：http://www.jianshu.com/users/64f479a1cef7/latest_articles

## APK下载
* fir.im下载
* 扫码下载


## 项目预览
*



## 项目简介
* 使用 MVP + Retrofit + RxJava进行架构
* 使用Lambda表达式精选代码
* 使用ResideMenu3D侧滑菜单
* 使用大量绚丽的Material Design控件
* 使用MultipleTheme实现无缝切换主题
* 使用Icon Font处理项目中大部分图标
* 使用Glide加载项目中的图片
* 使用Gson解析json数据生成实体bean
* 使用EventBus实现组件之间的通信
* 使用ButterKnife绑定id
* 使用EasyRecyclerView处理数据的展示
* 使用SwipeBackLayout实现返回销毁Activity
* 使用节操播放器实现视频播放
* 使用CardStack实现发现页面的绚丽卡片栈
* 使用CircleImageView显示圆形头像
* 使用NineGridView显示新闻里的图片
* 使用Fiddler抓包咪咕影院数据，干货和福利数据来自与gank.io，新闻数据来自于易源
* 使用Leak...

## 功能架构
* 视频
	* 精选
	* 专题
	* 发现
	* 我的
* 干货
    * 综合
    * Android
    * iOS
    * App
* 新闻
* 福利
* 架构图


## 开发日志
###  2016.10.20
* 对项目进行功能架构，大体分为三大模块
* 对模块进行细分，完成了功能架构
* 研究MVP模式实际项目中使用
* 研究RxJava+retrofit实际项目中使用
* 对咪咕影院进行抓包，整合视频API

###  2016.10.21


###  2016.10.22
* 初始化项目
* 将项目上传至GitHub，将项目上传至Coding
* 抽取APP,BaseActivity,BaseFragment等常见基类
* 整理常用工具类
* 集成ResideMenu框架，测试ResideMenu功能

### 2016.10.23



### 2016.11.1


### 2016.11.10
* 搭建干货界面UI框架
* 建立FragmentFactory工厂类生产API对应的Fragment
* 解决ViewPager与SwipeBackLayout的滑动冲突
* 完成干货界面的UI框架搭建


### 2016.11.11
* 对功能重新架构，将干货模块的福利功能分离成一个大功能模块
* 搭建干货界面model层，封装API
* 封装干货界面带有下拉刷新和上拉加载的条目基类
* 复用基类完成了所有干货页面的基本显示
* 完成干货界面的完整显示功能，实现了点击链接跳转


### 2016.11.12
* 研究Lambda表达式在android中的使用情况
* 使用Lambda表达式对大部分代码进行了重构，简化了代码逻辑
* 封装福利页面的API接口
* 加载福利页面的数据，使用RecyclerView的瀑布流布局和CardView条目展示
* 福利页面条目点击进入图片阅览
* 添加保存本地和分享功能
* 对代码进行了优化

### 2016.11.13
* 重构干货基类的适配器，完善了上拉刷新和下拉加载
* 对干货界面的Fab进行了抽取，使用EventBus完善了干货界面Fab的跳转
* 优化了阅图界面功能，实现了本地保存和分享图片
* 集成PhotoView实现图片的缩放和长按保存功能
* 对项目整体的UI图标进行了重构优化




## 遇到的难题
* SwipeDeck和ResideMenu的滑动冲突问题
> 问题描述：SwipeDeck左右切换时会直接作用到ResideMenu上
>
> 解决：将SwipeDeck直接添加到ResideMenu上忽略清单中，就可以让SwipeDeck自己处理内部事件。
ResideMenu会在dispatchTouchEvent方法里对忽略清单里的View进行事件处理。

* ViewPager和SwipeBackLayout的滑动冲突问题
> 问题描述：在可以又滑返回销毁的Activity中ViewPager是没有滑动事件的。
> 解决：自定义ViewPager，重写dispatchTouchEvent方法，判断左滑时请求不要拦截事件。

* 在侧滑菜单中切换主题后，Fragment中的控件不能切换主题。
> 问题描述：在MainActivity中切换主题后，在Fragment中并没有切换主题的代码，所以需要将修改主题的代码放置在BaseFragment中，
                                            然后在MainActivity发送切换主题的消息，BaseFragment订阅到事件后就切换主题。

* 集成PhotoView控件后，图片显示在左上角，不能居中。
> 解决：在布局文件中直接使用PhotoView，不用ImageView就好了。



## TODO
* 视频模块搜索功能
* 制作开发UML图

## 项目用到的第三方开源类库


## 开源协议
	Copyright 2016 PingerWan.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.