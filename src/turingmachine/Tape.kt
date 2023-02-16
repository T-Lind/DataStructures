package turingmachine

class Tape(var tapeLen: Int? = null, var tapeData: BooleanArray? = null)
{
    init {
        if(tapeData == null){
            tapeData = BooleanArray(tapeLen!!)
        }
        else if(tapeLen == null){
            tapeLen = tapeData!!.size
        }

    }
}