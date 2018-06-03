package xyz.sangcomz.stickytimeline

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import xyz.sangcomz.stickytimelineview.RecyclerSectionItemDecoration
import xyz.sangcomz.stickytimelineview.model.SectionInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get data
        val singerList = getSingerList()
        setVerticalList(singerList)
        setHorizontalList(singerList)


    }

    private fun setVerticalList(singerList: List<Singer>) {
        //Currently only LinearLayoutManager is supported.
        rv_main_vertical.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)

        //Add RecyclerSectionItemDecoration.SectionCallback
        rv_main_vertical.addItemDecoration(getSectionCallback(singerList))


        //Set Adapter
        rv_main_vertical.adapter = SingerAdapter(layoutInflater,
                singerList,
                R.layout.item_vertical)
    }

    private fun setHorizontalList(singerList: List<Singer>) {
        //Currently only LinearLayoutManager is supported.
        rv_main_horizontal.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false)

        //Add RecyclerSectionItemDecoration.SectionCallback
        rv_main_horizontal.addItemDecoration(getSectionCallback(singerList))


        //Set Adapter
        rv_main_horizontal.adapter = SingerAdapter(layoutInflater,
                singerList,
                R.layout.item_horizontal)
    }


    //Get data method
    private fun getSingerList(): List<Singer> = SingerRepo().singerList


    //Get SectionCallback method
    private fun getSectionCallback(singerList: List<Singer>): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            //In your data, implement a method to determine if this is a section.
            override fun isSection(position: Int): Boolean =
                    singerList[position].debuted != singerList[position - 1].debuted

            //Implement a method that returns a SectionHeader.
            override fun getSectionHeader(position: Int): SectionInfo? =
                    SectionInfo(singerList[position].debuted, singerList[position].group)
        }
    }
}
