package com.moya.funch.usecase

import com.moya.funch.entity.SubwayStation
import com.moya.funch.repository.SubwayRepository
import javax.inject.Inject

class LoadSubwayStationsUseCaseImpl @Inject constructor(
    private val subwayRepository: SubwayRepository
) : LoadSubwayStationsUseCase {

    override suspend operator fun invoke(subwayStation: String): Result<List<SubwayStation>> {
        return subwayRepository.fetchSubwayStations(subwayStation = subwayStation)
    }

}

fun interface LoadSubwayStationsUseCase {
    suspend operator fun invoke(subwayStation: String): Result<List<SubwayStation>>
}
