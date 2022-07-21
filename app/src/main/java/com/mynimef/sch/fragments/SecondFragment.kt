package com.mynimef.sch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mynimef.sch.adapters.CarouselAdapter
import com.mynimef.sch.databinding.FragmentSecondBinding
import com.mynimef.sch.models.BookInfo


class SecondFragment: Fragment() {

    private val viewModel: SecondFragmentViewModel by viewModels()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("book") { _, bundle ->
            viewModel.book.postValue(bundle.get("data") as BookInfo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonClose.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }

        viewModel.book.observe(viewLifecycleOwner) { book ->
            Glide
                .with(this)
                .load(book.image)
                .centerCrop()
                .transform(CenterCrop(), RoundedCorners(40))
                .into(binding.imageView)

            binding.textName.text = book.title
            binding.textAuthor.text = book.author
            binding.textRating.text = book.rate.score.toString()
            binding.textAmount.text = "(" + book.rate.amount.toString() + ")"
        }

        onCreateSimilarRecycler(binding.recyclerAlsoLike)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onCreateSimilarRecycler(similar: RecyclerView) {
        similar.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        similar.clipToOutline = true

        val adapter = CarouselAdapter()
        similar.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(similar)

        viewModel.getSimilar().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}