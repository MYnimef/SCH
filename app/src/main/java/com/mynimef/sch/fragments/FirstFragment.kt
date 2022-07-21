package com.mynimef.sch.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mynimef.sch.R
import com.mynimef.sch.adapters.BestSellerAdapter
import com.mynimef.sch.adapters.CarouselAdapter
import com.mynimef.sch.databinding.FragmentFirstBinding
import com.mynimef.sch.models.BookInfo


class FirstFragment: Fragment() {

    private val viewModel: FirstFragmentViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            //TODO
        }

        onCarouselCreated(binding.recyclerViewCarousel)
        onBestSellerCreated(binding.recyclerViewBestSeller)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onCarouselCreated(carousel: RecyclerView) {
        carousel.layoutManager = CustomLinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        carousel.clipToOutline = true

        val adapter = CarouselAdapter()
        carousel.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewCarousel)

        viewModel.getCarousel().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    class CustomLinearLayoutManager(
        context: Context?,
        orientation: Int,
        reverseLayout: Boolean
    ): LinearLayoutManager(context, orientation, reverseLayout) {

        private val mShrinkAmount = 0.15f
        private val mShrinkDistance = 0.9f

        override fun getPaddingLeft(): Int {
            return 80
        }

        override fun getPaddingRight(): Int {
            return (width / 2) - 100
        }

        override fun onLayoutCompleted(state: RecyclerView.State?) {
            super.onLayoutCompleted(state)
            scaleChildren()
        }

        override fun scrollHorizontallyBy(
            dx: Int,
            recycler: RecyclerView.Recycler?,
            state: RecyclerView.State?
        ): Int {
            return if (orientation == HORIZONTAL) {
                scaleChildren()
                super.scrollHorizontallyBy(dx, recycler, state)
            } else {
                0
            }
        }

        private fun scaleChildren() {
            val midpoint = height * 11 / 15 / 2 + 80
            val d1 = mShrinkDistance * midpoint
            for (i in 0 until childCount) {
                val child = getChildAt(i) as View
                val d = Math.min(d1, Math.abs(midpoint - (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f))
                val scale = 1f - mShrinkAmount * d / d1
                child.scaleX = scale
                child.scaleY = scale
            }
        }
    }

    private fun onBestSellerCreated(bestSeller: RecyclerView) {
        val changeFragment = { book: BookInfo ->
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                setCustomAnimations(
                    R.anim.slide_in_bottom,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out_bottom
                )
                add(R.id.nav_host_fragment, SecondFragment())
            }
        }

        bestSeller.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        bestSeller.clipToOutline = true

        val adapter = BestSellerAdapter(changeFragment)
        bestSeller.adapter = adapter

        viewModel.getBestSellers().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}
