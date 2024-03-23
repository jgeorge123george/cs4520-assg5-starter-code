package com.cs4520.assgn5.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cs4520.assgn5.repo.Repository

class ApiWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val repository = Repository(context)

    override suspend fun doWork(): Result {

        repository.loadProductsFromApi()


        return Result.success()
    }


}