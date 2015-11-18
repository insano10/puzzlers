package com.insano10.puzzlers.puzzles.woutercoekaerts.car;

public final class BetterCar
{
    private final int MAX_SPEED = 100;

    private int speed = 0;

    public synchronized void accelerate(int acceleration)
    {
        speed += acceleration;
//        System.out.println("speed is now: " + speed);
        if (speed > MAX_SPEED)
        {
            crash();
        }
    }

    public synchronized void crash()
    {
        speed = 0;
//        System.out.println("crash");
    }

    public synchronized void vroom()
    {
        if (speed > MAX_SPEED * 10)
        {
            // The goal is to reach this line
            System.out.println("Vroom!");
        }
    }
}