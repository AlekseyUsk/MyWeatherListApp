package com.hfad.myweatherlistapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.util.stream.Collectors

class Utils {
}

@RequiresApi(Build.VERSION_CODES.N)
fun getLines(reader: BufferedReader): String {
    return reader.lines().collect(Collectors.joining("\n"))
}
