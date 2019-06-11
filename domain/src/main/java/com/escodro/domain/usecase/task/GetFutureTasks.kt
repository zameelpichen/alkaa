package com.escodro.domain.usecase.task

import com.escodro.domain.mapper.TaskMapper
import com.escodro.domain.viewdata.ViewData
import com.escodro.local.provider.DaoProvider
import io.reactivex.Single
import java.util.Calendar

/**
 * Use case to get all tasks scheduled in the future that are not completed from the database.
 */
class GetFutureTasks(private val daoProvider: DaoProvider, private val mapper: TaskMapper) {

    /**
     * Gets all the uncompleted tasks in the future.
     *
     * @return observable to be subscribe
     */
    operator fun invoke(): Single<MutableList<ViewData.Task>> =
        daoProvider.getTaskDao()
            .getAllTasksWithDueDate()
            .flattenAsObservable { it }
            .filter { isInFuture(it.dueDate) }
            .map { mapper.toViewTask(it) }
            .toList()

    private fun isInFuture(calendar: Calendar?): Boolean {
        val currentTime = Calendar.getInstance()
        return calendar?.after(currentTime) ?: false
    }
}
