package com.laba1;
import java.io.*;
import java.util.Scanner;//импорт сканера

import static java.lang.Math.sqrt;

public class Laba1 {
    public static void main(String[] args)
    {
        Scanner enter = new Scanner(System.in);
/*
System.out.println("First points: ");
double x1=enter.nextInt();
double y1=enter.nextInt();
double z1=enter.nextInt();
System.out.println("Second points: ");
double x2=enter.nextInt();
double y2=enter.nextInt();
double z2=enter.nextInt();
System.out.println("Third point: ");
double x3=enter.nextInt();
double y3=enter.nextInt();
double z3=enter.nextInt();

Point3d VAL1=new Point3d(x1,y1,z1);
Point3d VAL2=new Point3d(x2,y2,z2);
Point3d VAL3=new Point3d(x3,y3,z3);
*/

        Point3d mas[] = new Point3d[3];

        for (int i = 0; i <= 2; i++)
        {
            mas[i] = new Point3d();
            System.out.println("Enter x: ");
            mas[i].setX(enter.nextDouble());
            System.out.println("Enter y: ");
            mas[i].setY(enter.nextDouble());
            System.out.println("Enter z: ");
            mas[i].setz(enter.nextDouble());
        }
        if (mas[0].comparisonofxyz(mas[1]) || mas[0].comparisonofxyz(mas[2]) || mas[1].comparisonofxyz(mas[2]))

            System.out.println("Точки эквивалентны!");

        else
        {
            System.out.println("Площадь треугольника" +computeArea(mas));
        }
    }

    public static double computeArea(Point3d mas[])
    {
        double a=mas[0].distanceTo(mas[1]);
        double b=mas[0].distanceTo(mas[2]);
        double c=mas[1].distanceTo(mas[2]);
        double p=(a+b+c)/2;
        return(sqrt(p*(p-a)*(p-b)*(p-c)));
    }
}
