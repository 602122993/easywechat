## ***easy-wechat 快速微信开发工具***

## 简介
该项目旨在缩减微信相关的开发周期,由于微信方提供的api过于繁琐,调用流程过长
,坑也过多,而微信方也没有具体的工具依赖,于是有了该项目,让用户可以方便快捷的接入微信公众号以及微信支付的api

## 框架依赖

该工具目前依赖Spring boot 2.0,方便引入配置文件,后期重写配置工具,使该项目不依赖Spring

通用工具采用[hutool工具包](https://github.com/looly/hutool)

依赖lombok快速开发实体类

Http工具采用Apache的httpcomponents

## 快速开始

### 安装

该项目基于Spring boot 2.0以上版本 , JDK版本8+ 

maven:
```xml
<dependency>
  <groupId>com.xiaoazhai</groupId>
  <artifactId>easy-wechat</artifactId>
  <version>0.0.2</version>
</dependency>
```


### 添加配置

将easy-wechat的配置引入项目 在配置类中加入
```java
@Configuration
@Import(WxPayConfig.class)
public class BaseConfig {

}
```

在application.yml配置文件中添加微信相关的配置数据
,该配置提供了统一通用的商户号,以及统一的商户秘钥,也可以为APP,小程序以及公众号单独配置商户号和秘钥

本项目不仅提供配置文件配置,也支持在请求中传入对应的appid以及秘钥
```yaml
wx:
  mch-id:   #统一的商户号
  mch-secret:  #统一的商户秘钥
  app-id:   # app支付的appid
  app-secret: # app支付的APP秘钥
  app-mch-id: # APP单独的商户号,如果用通用的可以不配置该参数
  app-mch-secret: # APP独立的商户秘钥,如果用通用的可以不配置该参数
  we-app-id:  # 小程序appid
  we-app-secret:  # 小程序APP秘钥
  we-app-mch-id:  # 小程序单独的商户号,如果用通用的可以不配置该参数
  we-app-mch-secret:  #小程序独立的商户秘钥,如果用通用的可以不配置该参数
  pub-app-id:   #公众号的appid
  pub-app-secret:  #公众号的app秘钥
  pub-mch-id: # 公众号单独的商户号,如果用通用的可以不配置该参数
  pub-mch-secret: #公众号独立的商户秘钥,如果用通用的可以不配置该参数
  aes-key: #公众号加密秘钥 既微信公众号后台中配置的EncodingAESKey
  ase-secret: #是否开启加密 true表示开启
  pub-token: #公众号后台配置token
```

## 微信公众号开发

本文档依赖于[微信公众号开发文档](https://developers.weixin.qq.com/doc/offiaccount/Getting_Started/Overview.html)
所有的参数与异常皆依赖于该文档.

所有请求皆由对应的Request利用建造者模式填入所有参数 并且返回对应的response

所有Response皆继承ErrorResponse,该类中的errcode,errmsg映射对应的错误码以及描述

微信公众号开发中大部分接口都是需要access_token的,开发者使用中控服务器统一获取和刷新access_token，其他业务逻辑服务器所使用的access_token均来自于该中控服务器，不应该各自去刷新，否则容易造成冲突，导致access_token覆盖而影响业务
,该项目缓存了access_token但是并未检测access_token的有效性,所以如果开发者用统一公众号的appid获取access_token会导致该服务失效,
本项目提供了刷新access_token的功能

以下所有参数说明皆针对本项目而不针对微信开发文档,本项目将许多常用的参数默认填写,
所以有些参数是否必须可能与微信开发文档不同

### 开始开发

#### 获取AccessToken 

参数说明

| 参数 | 是否必须 | 参数说明 |
| :----: | :----: |  :----:  |
| grant_type | 否 | 获取access_token填写client_credential 已自动填写 |
|appid|否|第三方用户唯一凭证|
|secret|否|第三方用户唯一凭证密钥，即appsecret|

示例代码
```
//根据配置文件中的配置获取access_token
AccessTokenResponse response = WxPubUtil.getAccessToken();
//根据自己填入的appid以及APPsecret
AccessTokenResponse response = WxPubUtil.getAccessToken(AccessTokenRequest.builder()
                .appid("appid")
                .secret("appsecret").build())

```

获取access_token后,本项目会自动缓存,获取时也会根据appid自动获取缓存
<span id="jump">跳转到的地方</span>
###  用户管理

#### 获取用户基本信息(unionid机制)

请求参数说明

| 参数 | 是否必须 | 参数说明 |
| :----: | :----: |  :----  |
|access_token|否|调用接口凭证|
|openid|是|普通用户的标识，对当前公众号唯一|

返回参数说明

|参数|说明|
| :----: | :---- | 
|subscribe|用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。|
|openid|用户的标识，对当前公众号唯一|
|nickname|用户昵称|
|sex|用户的性别，值为1时是男性，值为2时是女性，值为0时是未知|
|city|用户所在城市|
|country|用户所在国家|
|province|用户所在省份|
|language|用户的语言，简体中文为zh_CN|
|headimgurl|用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。|
|subscribeTime|用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间|
|unionid|只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段|
|remark|公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注|
|groupid|用户所在的分组ID（兼容旧的用户分组接口）|
|tagidList|用户被打上的标签ID列表|
|subscribeScene|返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENE_PROFILE_LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他|
|qrScene|二维码扫码场景（开发者自定义）|
|qrSceneStr|二维码扫码场景描述（开发者自定义）|

代码示例
[点击跳转](#jump)
```
//根据默认配置的appid 以及openid获取用户信息
WxUserInfoResponse response = WxPubUtil.getWxPubUserInfoByOpenId("openid");
//根据传入的access_token以及openid获取用户信息
WxUserInfoResponse response = WxPubUtil.getWxPubUserInfoByOpenId("openid","access_token");
//根据传入的appid和openid获取用户信息
WxUserInfoResponse response = WxPubUtil.getWxPubUserInfoByOpenId("openid",AccessTokenRequest.builder().appid("appid").secret("secret").build());

``` 
### 消息管理

#### 接收普通消息+被动回复用户消息+接受事件推送
本项目采用基于事件类型的处理模式,根据微信方推送的不同事件,如文字消息,图片消息等等,采用不同的处理Handler,
首先在微信公众号管理后台**开发** -> **基本配置**中配置接入的url,token以及encodingAESKey,并选择对应的消息加密方式,如果选择了安全模式,
则需要在配置文件中

示例1:  接收文字消息  首先创建一个
```java



```



### 微信网页开发

####   根据code获取openid

返回结果说明

|参数|说明|
| :----: | :---- | 
|accessToken|网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同|
|refreshToken|用户刷新access_token|
|openid|用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID|

代码示例
```
AccessTokenResponse response  = WxPubUtil.getOpenIdByCode("code");
String openId = response.getOpenid();
```

#### 根据code获取用户信息(需scope为 snsapi_userinfo)
返回结果说明


|参数|说明|
| :----: | :---- | 
|openid|用户的标识，对当前公众号唯一|
|nickname|用户昵称|
|sex|用户的性别，值为1时是男性，值为2时是女性，值为0时是未知|
|city|用户所在城市|
|country|用户所在国家|
|province|用户所在省份|
|headimgurl|用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。|
|privilege|用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）|
|unionid|只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段|

代码示例

```
WxUserInfoResponse response = WxPubUtil.getWxUserInfoByCode(code);
```

#### jssdk获取签名


请求参数描述

|参数|是否必传|说明|
| :----: |:----: | :---- | 
|url|是|当前网页的url|
   
返回结果示例
   
|参数|说明|
| :----: | :---- | 
|noncestr|随机字符串|
|timestamp|时间戳|
|sign|签名结果|
|appId|微信公众号appid|

代码示例

```
//使用配置文件默认的appid请求
JSSDKResponse response = WxPubUtil.getJsSign(url);
//使用传入的access_token
JSSDKResponse response = WxPubUtil.getJsSign(accessToken,url);
//使用传入的appid和APPsecret
JSSDKResponse response = WxPubUtil.getJsSign(AccessTokenRequest.builder().appid(appid).secret(secret).build(),url);

```

## 微信支付开发


### 微信H5支付

请求参数描述

|参数|是否必传|说明|
| :----: |:----: | :---- |
|appid|否|微信分配的公众账号ID（企业号corpid即为此appId）在配置文件中配置好了即可不传
|mchId|否|微信支付分配的商户号|
|deviceInfo|否|	终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"|
|body|是|商品简单描述，该字段须严格按照规范传递，具体请见[参数设定](https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2)|
|attach|否|附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据|
|outTradeNo|是|商户系统内部的订单号,32个字符内、可包含字母|
|totalFee|是|订单总金额，单位为分|
|timeStart|否|订单生成时间|
|timeEnd|否|订单失效时间  注意：最短失效时间间隔必须大于5分钟|


