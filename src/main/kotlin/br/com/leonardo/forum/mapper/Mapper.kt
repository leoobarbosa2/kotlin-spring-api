package br.com.leonardo.forum.mapper

interface Mapper<T, U> {
    fun map(objeto: T): U
}
