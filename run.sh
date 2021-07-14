#!/bin/sh

# Help info function
help(){
  echo "--------------------------------------------------------------------------"
  echo ""
  echo "usage: ./run.sh [install | doc | package]"
  echo ""
  echo "-install    Install to your local Maven repository."
  echo "-doc        Generate Java doc api, you can see it in target dir"
  echo "-package       Make jar package by Maven"
  echo ""
  echo "--------------------------------------------------------------------------"
}

# Start
./bin/logo.sh
case "$1" in
  'install')
    bin/install.sh
	;;
  'doc')
    bin/javadoc.sh
	;;
  'pack')
    bin/package.sh
	;;
  'fast_pack')
    bin/fast_package.sh
	;;
  *)
    help
esac
