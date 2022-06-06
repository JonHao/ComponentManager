package top.jonhao.cm.version

import top.jonhao.cm.version.contract.PrivateLibraryContract
import top.jonhao.cm.version.entity.PrivateLibraryEntity

object PrivateLibraryEntities {
    object CM : PrivateLibraryContract.CM<PrivateLibraryEntity> {
        override val base = PrivateLibraryEntity.get("top.jonhao.cm", "base")
        override val publish = PrivateLibraryEntity.get("top.jonhao.cm", "publish")
        override val version = PrivateLibraryEntity.get("top.jonhao.cm", "version")
        override val common = PrivateLibraryEntity.get("top.jonhao.cm", "common")
    }
}