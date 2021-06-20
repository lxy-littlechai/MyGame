# Frame_work

## Tips

- 对于不变量应使用static final
- 注意私有变量private定义,不变量final static
- 要写注释）
- **及时交流补充方法及成员变量，更新文档**

## 参考链接

*https://github.com/pkloi/Breakout/tree/master/src*

## Music

#### 成员变量

```java
String filepath
Clip clip
```

#### 方法 

```
构造方法 Music(String filepath);

playMusic();	播放音乐
stopMusic();	停止音乐播放

```

#### 前置知识

```
AudioInputStream 音乐输入流
Clip类
好像还有qwq
```

#### 音乐素材

存放在music文件夹下

```
游戏背景
砖块击打 {
	普通击打
	穿刺球
	火焰球
	炸弹球
}
游戏失败
游戏胜利
丢失生命
```

## Brick

#### 成员变量

```
int Brick_w, Brick_h 砖块大小(固定值)
int Brick_x, Brick_y 砖块坐标
int live 生命值
String color 砖块颜色
```

#### 方法

```
void Brick(x, y, live) 构造函数，初始化砖块的位置，生命值
void draw(Graphics g) 根据砖块颜色和位置在界面绘制图形
Boolean isLive() 是否判断是否存活
int getX(),getY() 获取坐标
void setColor(String color) 设置颜色
```

#### 前置知识

```
JFrame 等框架制作api
Graphics类极其子类 Graphics2D
Image类
```



## Ball

#### 成员变量

```
int radius 小球半径
int x, y 小球位置,注意要给初始位置(和挡板一致)
int speed_x, speed_y x,y方向上的速度
String kind {小球种类
	"normal" 普通球
	"through" 穿刺球
	"fire" 火焰球
	"explode" 炸弹球
}
可添加操作
int pre_speed_x,pre_speed_y 记录x,y方向速度，用于暂停
```

#### 方法

```
void draw(Graphics g) 绘制小球
void collide(x, y, w, h) 
	判断碰撞，如果碰撞，判断碰撞状态，改变小球速度
void kind_change(String kind) 改变小球种类
Boolean colide_margin() 判断小球是否弹到边框，改变状态，注意弹到下边框返回true
```

#### 前置知识

```
JFrame 等框架制作api
Graphics类极其子类 Graphics2D
Image类
```



## Paddle

#### 成员变量

```
int x, y; 位置，设置初始位置
int paddle_w, paddle_w; 板的长度,高度
int speed;
```

#### 方法

```
draw(Graphics g) 绘制paddle
void setPosition(x, y) 设置初始位置
int getX(), getY()获取x,y坐标
void moveLeft() 板子移动
void moveRight()
void getEvent(String eventKind)
```

#### 前置知识

```
JFrame 等框架制作api
Graphics类极其子类 Graphics2D
Image类
```



## show Components 

extends Jcomponent

#### 成员变量

```
Paddle paddle
Ball ball
ArrayList<Brick> bricks;
ArrayList<Event> events;
```

#### 方法

```
showComponents(paddle, ball, bricks, event) 初始化
paint(Graphics g) 调用小球等draw()绘制
```



## Game 

- 构建游戏界面，添加score, heart(**JLable**)以及各种已写class
- 调用各种类，构建游戏逻辑
- 使用键盘监听
- extends JFrame implements ActionListener, KeyListener

## Home

创建主界面,具有"按下空格开始游戏"类似功能即可

#### 成员变量

```
int home_w 
int home_h	界面长度高度等
```

#### 方法

```
void gameStart(); 开始游戏
void musicStart(); 播放音乐
void sethomeFrame(); 主界面窗口设置
等等。。。
```

#### 前置知识

```
JFrame 等框架制作api
Graphics类极其子类 Graphics2D
```

## Event

#### 成员变量

```
int x, y 随机物品的初始掉落位置
int speed 物品掉落速度
String kind {
	"through" 穿刺球
	"fire" 火焰球
	"explode" 炸弹球
	"paddlex2" 板*2
    "paddle_2" 板/2
    "ball_shrink" 球缩小一倍
    "ball_over" 球变大倍
    "dead" 死亡
}
```

#### 方法

```
Event();构造函数,生成随机事件
draw(Graphics g);绘制随机事件
```



## Image(文件夹)

小球等各种元件的样式，放在imges文件夹中

## Others

- 统一界面大小
- 统一paddle，ball，brick的初始参数
- 等等等