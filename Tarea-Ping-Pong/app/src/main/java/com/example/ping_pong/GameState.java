package com.example.ping_pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;

public class GameState {

    //screen width and height
    final int _screenWidth = 1100;
    final int _screenHeight = 1400;

    //The ball
    final int _ballSize = 50;
    int _ballX = 100; 	int _ballY = 100;
    int _ballVelocityX = 3; 	int _ballVelocityY = 3;

    //The bats
    final int _batLength = 200;	final int _batHeight = 30;
    int _topBatX = (_screenWidth/2) - (_batLength / 2);
    final int _topBatY = 100;
    int _bottomBatX = (_screenWidth/2) - (_batLength / 2);
    final int _bottomBatY = 1200;
    final int _batSpeed = 20;

    public GameState()
    {
    }

    //The update method
    public void update() {

        _ballX += _ballVelocityX;
        _ballY += _ballVelocityY;

//DEATH!
        if(_ballY > _screenHeight || _ballY < 0)
        {_ballX = 100; 	_ballY = 100;}  	//Collisions with the sides

        if(_ballX > _screenWidth || _ballX < 0)
            _ballVelocityX *= -1; 	//Collisions with the bats

        if(_ballX > _topBatX && _ballX < _topBatX+_batLength && _ballY < _topBatY)
            _ballVelocityY *= -1;  //Collisions with the bats

        if(_ballX > _bottomBatX && _ballX < _bottomBatX+_batLength
                && _ballY > _bottomBatY)
            _ballVelocityY *= -1;
    }

    public boolean keyPressed(int keyCode)
    {
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) //left
        {
//            _topBatX += _batSpeed;
            _bottomBatX -= _batSpeed;
        }

        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) //right
        {
            _topBatX -= _batSpeed; _bottomBatX += _batSpeed;
        }

        return true;
    }

    //the draw method
    public void draw(Canvas canvas, Paint paint) {


//Clear the screen
        canvas.drawRGB(20, 20, 20);

//set the colour
        paint.setARGB(175, 252, 221, 0);

//draw the ball
        canvas.drawRect(new Rect(_ballX,_ballY,_ballX + _ballSize,_ballY + _ballSize),
                paint);

//draw the bats
//        canvas.drawRect(new Rect(_topBatX, _topBatY, _topBatX + _batLength,
//                _topBatY + _batHeight), paint); //top bat
        canvas.drawRect(new Rect(_bottomBatX, _bottomBatY, _bottomBatX + _batLength,
                _bottomBatY + _batHeight), paint); //bottom bat

    }
}