#!/bin/sh

set -o errexit

pwd=$(pwd)

echo "当前路径：${pwd}"

if [ -n "$1" ];then
    new_version="$1"
    old_version=`cat ${pwd}/bin/version.txt`
    echo "$old_version 替换为新版本 $new_version"
else
    # 参数错误，退出
    echo "ERROR: 请指定新版本！"
    exit
fi

if [ ! -n "$old_version" ]; then
    echo "ERROR: 旧版本不存在，请确认bin/version.txt中信息正确"
    exit
fi

# 替换README.md中的版本
sed -i "" "s/${old_version}/${new_version}/g" $pwd/README.md >/dev/null 2>&1
sed -i "" "s/${old_version}/${new_version}/g" $pwd/README-EN.md >/dev/null 2>&1
# 替换docs/js/version.js中的版本
sed -i "" "s/${old_version}/${new_version}/g" $pwd/docs/js/version.js >/dev/null 2>&1

# 保留新版本号
echo "$new_version" > $pwd/bin/version.txt
