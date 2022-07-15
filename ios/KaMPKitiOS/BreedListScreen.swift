//
//  BreedListView.swift
//  KaMPKitiOS
//
//  Created by Russell Wolf on 7/26/21.
//  Copyright © 2021 Touchlab. All rights reserved.
//

import Combine
import SwiftUI
import shared

private let log = koin.loggerWithTag(tag: "ViewController")

struct BreedListScreen: View {
    @ObservedObject
    var observableModel = BallastObservable<
        BreedContract.Inputs,
        BreedContract.Events,
        BreedContract.State>(
            viewModelFactory: KotlinDependencies.shared.getBreedViewModel,
            eventHandlerFactory: nil
        )

    var body: some View {
        BreedListContent(
            vmState: observableModel.vmState,
            postInput: observableModel.postInput
        )
        .withViewModel(observableModel) {
            observableModel.postInput(BreedContract.InputsRefreshBreeds(forceRefresh: false))
        }
    }
}

struct BreedListContent: View {
    var vmState: BreedContract.State
    var postInput: (BreedContract.Inputs) -> Void

    var body: some View {
        ZStack {
            VStack {
                if let breeds = vmState.breedsList {
                    List(breeds, id: \.id) { breed in
                        BreedRowView(breed: breed) {
                            postInput(BreedContract.InputsUpdateBreedFavorite(breed: breed))
                        }
                    }
                }
                if let error = vmState.error {
                    Text(error)
                        .foregroundColor(.red)
                }
                Button("Refresh") {
                    postInput(BreedContract.InputsRefreshBreeds(forceRefresh: true))
                }
            }
            if vmState.isLoading { Text("Loading...") }
        }
    }
}

struct BreedRowView: View {
    var breed: Breed
    var onTap: () -> Void

    var body: some View {
        Button(action: onTap) {
            HStack {
                Text(breed.name)
                    .padding(4.0)
                Spacer()
                Image(systemName: (!breed.favorite) ? "heart" : "heart.fill")
                    .padding(4.0)
            }
        }
    }
}

struct BreedListScreen_Previews: PreviewProvider {
    static var previews: some View {
        BreedListContent(
            vmState: BreedContract.State(
                breeds: CachedValue(
                    value: [
                        Breed(id: 0, name: "appenzeller", favorite: false),
                        Breed(id: 1, name: "australian", favorite: true)
                    ]
                )
            ),
            postInput: { input in }
        )
    }
}
