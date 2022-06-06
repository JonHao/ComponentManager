import top.jonhao.cm.version.contract.PrivateLibraryContract
import top.jonhao.cm.version.PrivateLibraryEntities

object PrivateLibraries {
    object CM : PrivateLibraryContract.CM<String> {
        override val base = PrivateLibraryEntities.CM.base.toString()
        override val publish = PrivateLibraryEntities.CM.publish.toString()
        override val version = PrivateLibraryEntities.CM.version.toString()
        override val common = PrivateLibraryEntities.CM.common.toString()
    }
}