package androidx.databinding

/**
 * This is a work around since this class wasn't being generated during roboletric tests.
 * See https://github.com/robolectric/robolectric/issues/3789 for details.
 */
class DataBinderMapperImpl() : MergedDataBinderMapper() { init {
    addMapper(com.example.browse.DataBinderMapperImpl())
}
}