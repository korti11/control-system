package at.jku.ctc.entity

data class Street(val name: String = "", val length: Int = -1, val addresses: List<Address> = emptyList())