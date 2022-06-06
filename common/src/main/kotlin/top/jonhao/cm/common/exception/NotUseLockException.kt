package top.jonhao.cm.common.exception

class NotUseLockException :
    RuntimeException("Please configuration `cm.useLock=true` or `Common.setUseLock(true)`")