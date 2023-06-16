package com.hoa.clean_architecture.data.remote

import com.hoa.clean_architecture.data.ApiException
import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.SourceType
import com.hoa.clean_architecture.data.model.MenuItem
import kotlin.random.Random

class MenuItemApiServiceImpl : MenuItemApiService {

    override suspend fun getMenuItem(itemId: Int): ApiResponse<MenuItem> {
        return ApiResponse.Success(
            MenuItem(
                id = 1,
                name = "Freeze Matcha Green Tea",
                description = "Để thưởng thức Matcha “xịn” , mời bạn dùng thử Matcha Satoen Nhật Bản nhé !~ Bạn sẽ hiểu được tại sao Highland Coffee lại tin dùng Satoen cho thức uống Best Seller của họ, dù chi phí có cao hơn dùng Matcha thường, nhưng đẳng cấp của thứ thức uống ngon bậc nhất Sài Thành thì không thể lẫn được .\n" +
                    "- Màu chuẩn : Màu xanh ngọc bích do chứa nhiều chất diệp lục, không phải màu xanh đậm xỉn màu như Matcha Đài Loan.\n" +
                    "- Mùi : Matcha Satoen có mùi tanh của lá như rong biển, còn matcha kém chất lượng thường ướp hương nhài để át đi mùi gắt của lá trà.\n" +
                    "- Độ mịn : Bột trà xịn rất mịn không cảm nhận được bằng tay. Còn trà thông thường còn có cả cọng, thân xay lẫn, nên sờ thấy.\n" +
                    "- Vị chuẩn : vị thanh, hậu ngọt, không bị chát, hậu ko bị đắng như matcha kém.\n" +
                    "- Cảm thấy tăng lực, tỉnh táo, hồi phục sức khỏe sau khi uống.",
                isFavorite = false
            ),
            SourceType.REMOTE
        )
    }

    override suspend fun addItemToFavorite(item: MenuItem): ApiResponse<Any> {
        return when (Random.nextInt() % 2) {
            0 -> ApiResponse.Success(Any())
            else -> ApiResponse.Error(ApiException("Something went wrong!!"))
        }
    }

    override suspend fun unFavorite(item: MenuItem): ApiResponse<Any> {
        return when (Random.nextInt() % 2) {
            0 -> ApiResponse.Success(Any())
            else -> ApiResponse.Error(ApiException("Something went wrong!!"))
        }
    }
}