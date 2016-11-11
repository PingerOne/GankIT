# GankIT
> 个人开源项目，基于 MVP + Retrofit + RxJava 进行架构，主要包括视频，干货，新闻这三个大模块。
>
> 个人博客：http://www.jianshu.com/users/64f479a1cef7/latest_articles

## 项目预览
*



## 项目简介
* 使用 MVP + Retrofit + RxJava进行架构
* 使用了大量绚丽的Material Design控件
* 使用ResideMenu实现3D侧滑菜单
* 使用MultipleTheme实现无缝切换主题
* 使用Icon Font处理项目中的图标问题
* 使用EasyRecyclerView处理数据条目
* 电影数据抓包自咪咕影院
* 干货数据来自Gank.io
* 新闻数据来自易源
* 使用isparta工具将资源图片转换成webP格式，对APK进行了瘦身



## 功能架构
* 视频
	* 精选
	* 专题
	* 发现
	* 我的
* 干货
* 新闻

* 架构图


## 项目UML图




## 开发日志
###  2016.10.20
* 对项目进行功能架构，大体分为三大模块
* 对模块进行细分，完成了功能架构
* 研究开源项目的MVP模式实际项目中使用
* 研究RxJava+retrofit具体项目中使用


###  2016.10.22
* 初始化项目
* 基于MVP建包并抽取基类

### 2016.10.23



### 2016.11.1


### 2016.11.10
* 搭建干货界面UI框架
* 建立FragmentFactory工厂类生产API对应的Fragment
* 解决ViewPager与SwipeBackLayout的滑动冲突
* 完成干货界面的UI框架搭建


### 2016.11.11
* 搭建干货界面model层，封装API
* 封装干货界面带有下拉刷新和上拉加载的条目基类
* 复用基类完成了所有干货页面的基本显示




## 遇到的难题
* SwipeDeck和ResideMenu的滑动冲突问题
> 问题描述：SwipeDeck左右切换时会直接作用到ResideMenu上
>
> 解决：将SwipeDeck直接添加到ResideMenu上忽略清单中，就可以让SwipeDeck自己处理内部事件。
ResideMenu会在dispatchTouchEvent方法里对忽略清单里的View进行事件处理。

* ViewPager和SwipeBackLayout的滑动冲突问题
> 问题描述：在可以又滑返回销毁的Activity中ViewPager是没有滑动事件的。
> 解决：自定义ViewPager，重写dispatchTouchEvent方法，判断左滑时请求不要拦截事件。

* 在侧滑菜单中切换主题后，Fragment中的控件不能切换主题
> 问题描述：在MainActivity中切换主题后，在Fragment中并没有切换主题的代码，所以需要将修改主题的代码放置在BaseFragment中，然后在MainActivity发送切换主题的消息，BaseFragment收到消息后就切换主题

* 抽取干货基类时，上拉加载更多出现空指针，View层的加载更多方法会调用Presenter层的加载数据方法，但是Presneter层还没有走完绑定View的代码，对象是个空的，所以出现了错误




## TODO
* 视频模块的搜索功能


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