package me.dwidar.aflamy.shell.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.dwidar.aflamy.core.repo.casts_repo.CastsRepo
import me.dwidar.aflamy.core.repo.movies_repo.MoviesRepo
import me.dwidar.aflamy.shell.repo.casts_repo.CastsRepoImpl
import me.dwidar.aflamy.shell.repo.movies_repo.MoviesRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    @Provides
    @Singleton
    fun provideMoviesRepo(): MoviesRepo {
        return MoviesRepoImpl()
    }

    @Provides
    @Singleton
    fun provideCastsRepo(): CastsRepo {
        return CastsRepoImpl()
    }

}