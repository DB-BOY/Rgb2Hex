#!/bin/bash

read -p "输入A、R、G、B的数值，逗号(,)分割：" input
read -p "输入透明度百分比(0~1之间)：" flo
OLD_IFS="$IFS"
IFS=","
arr=($input)

IFS="$OLD_IFS"

a=${arr[0]}
r=${arr[1]}
g=${arr[2]}
b=${arr[3]}

if [ $a -gt 255 ]
then a=255
fi

if [ $r -gt 255 ]
then r=255
fi

if [ $g -gt 255 ]
then g=255
fi

if [ $b -gt 255 ]
then b=255
fi


if [ $a -lt 0 ]
then a=0
fi

if [ $r -lt 0 ]
then r=0
fi

if [ $g -lt 0 ]
then g=0
fi

if [ $b -lt 0 ]
then b=0
fi



a=$(echo "$a*$flo" | bc)

a=$(printf "%.0f\n" $a)
# printf "alpha%X" $a
# echo ""
a=$(printf "%02X" $a)
r=$(printf "%02X" $r)
g=$(printf "%02X" $g)
b=$(printf "%02X" $b)
echo "$a$r$g$b"

