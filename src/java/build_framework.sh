#!/bin/bash

class_path="."
output_dir="to_jar"
output_jar_fileName="myFramework.jar"
testFrameworkPath="../../testFramework"

echo "class_path = $class_path"
echo "output_dir = $output_dir"

# Compiling to class_files

javac --source 8 -d $output_dir --source-path . --class-path "$class_path" ./etu001844/framework/servlet/FrontServlet.java
jar --create --file "$output_jar_fileName" -C "$output_dir" .
rm -rf $output_dir
if [ -e $testFrameworkPath ]
then
	rm $testFrameworkPath/lib/$output_jar_fileName
fi
mv ./$output_jar_fileName $testFrameworkPath/lib
