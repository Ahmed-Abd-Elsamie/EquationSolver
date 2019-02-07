# Java algorithm to solve any number of linear equations

This Algorithm allow users to to solve any number of equation with any variables names
For example you can write the equations Like ==> x1+x2-2x3+x4+3x5-x6=4
or like this == > (ahmed+mohamed-25abdo+98y+3z-22x=154ahmed+54abdo-456)
you can put any variable name and repate the terms then the program will arrange your equation and make it ideal and solve your System of Equations

Execution time : it can solve up to 1000 equation in 1000 variable only in 3 seconds.


Example
</br>
Please enter the number of equations : 6
</br>
equation 1 : 12x1+7x2-2x3-98x3+x4+3x5-x6=4+84x1-98
</br>
equation 2 : 45x1+98x2-98x3=45-98x1+2x5
</br>
equation 3 : 57x1+98x2-89x6=45x1+87x3-54x4-2x5
</br>
equation 4 : -x1+98x2+95x2+98x4=52x6+98
</br>
equation 5 : -x6+45x5+98x1+8=51+95x2+23x2-98x3
</br>
equation 6 : 21x4+56x3-98x1+59=12x2+98x6-4+123
</br>

Arranged Equations : 
</br>
-72.0x1+7.0x2-100.0x3+1.0x4+3.0x5-1.0x6=-94.0
</br>
143.0x1+98.0x2-98.0x3-2.0x5=45.0
</br>
12.0x1+98.0x2-87.0x3+54.0x4+2.0x5-89.0x6=-0.0
</br>
-1.0x1+193.0x2+98.0x4-52.0x6=98.0
</br>
98.0x1-118.0x2+98.0x3+45.0x5-1.0x6=43.0
</br>
-98.0x1-12.0x2+56.0x3+21.0x4-98.0x6=60.0
</br>

Variables Names : 
</br>
x1
</br>
x2
</br>
x3
</br>
x4
</br>
x5
</br>
x6
</br>
Matrix : 
</br>

-72.0   7.0   -100.0   1.0   3.0   -1.0   |  -94.0
</br>
143.0   98.0   -98.0   0.0   -2.0   0.0   |  45.0
</br>
12.0   98.0   -87.0   54.0   2.0   -89.0   |  -0.0
</br>
-1.0   193.0   0.0   98.0   0.0   -52.0   |  98.0
</br>
98.0   -118.0   98.0   0.0   45.0   -1.0   |  43.0
</br>
-98.0   -12.0   56.0   21.0   0.0   -98.0   |  60.0
</br>

Row Echelon form : 
</br>
143.000 98.000 -98.000 0.000 -2.000 0.000 | 45.000
</br>
0.000 193.685 -0.685 98.000 -0.014 -52.000 | 98.315
</br>
-0.000 0.000 164.506 93.687 46.357 -50.711 | 106.148
</br>
0.000 0.000 0.000 57.430 44.025 -31.849 | -3.706
</br>
0.000 0.000 0.000 0.000 -16.543 -59.548 | 4.716
</br>
0.000 0.000 0.000 0.000 0.000 -94.978 | 70.509
</br>


Solution : 
</br>
x1=0.0589017
</br>
x2=1.4790491
</br>
x3=1.0570984
</br>
x4=-2.3061304
</br>
x5=2.3870537
</br>
x6=-0.7423692
</br>
