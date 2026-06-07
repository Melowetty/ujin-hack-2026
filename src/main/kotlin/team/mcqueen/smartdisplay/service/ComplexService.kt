package team.mcqueen.smartdisplay.service

import team.mcqueen.smartdisplay.generated.model.Complex

interface ComplexService {
    fun getComplexList(): List<Complex>
    fun getComplexByHouseId(houseId: Long): Complex?
}