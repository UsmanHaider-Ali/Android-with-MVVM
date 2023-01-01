package com.leadingspark.mvvmpracticesession.utils

import java.io.IOException

class APIExceptions(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)