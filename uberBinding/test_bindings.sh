#!/bin/sh

rm -rf .build
mkdir .build
xjc src/main/xsd/ogc/xlink/1.0.0/xlinks.xsd schemas/test_fails.xsd -b xjb/test.xjb -extension -d .build -catalog catalog/ogc-catalog.xml -verbose > out.txt
xjc src/main/xsd/xlink/1.0.0/xlinks.xsd schemas/test_works.xsd -b xjb/xlink.xjb -extension -d .build -catalog catalog/ogc-catalog.xml -verbose > out2.txt
