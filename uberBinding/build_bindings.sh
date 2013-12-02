#!/bin/sh

rm -rf .build
mkdir .build
xjc src/main/xsd/aixm/AIXM_Features.xsd -b src/main/xjb -extension -d .build
